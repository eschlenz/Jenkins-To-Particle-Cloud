package com.ericschlenz.jenkinssync.client;

import com.ericschlenz.jenkinssync.Environment;
import com.ericschlenz.jenkinssync.domain.Job;
import com.ericschlenz.jenkinssync.domain.ParticlePublish;
import com.ericschlenz.jenkinssync.domain.ParticleResponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import javax.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class ParticleClientImpl implements ParticleClient {
    private static final Logger L = LogManager.getLogger(ParticleClientImpl.class);
    private static final String PARTICLE_API = "https://api.particle.io/v1/devices/events";
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String HEADER_AUTHORIZATION_VALUE = "Bearer %1$s";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_CONTENT_TYPE_VALUE = "application/json";
    private static final String DATA_FORMAT = "%1$s:%2$s";

    private final Environment environment;

    public ParticleClientImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public ParticleResponse publishEvent(Job job) {
        HttpResponse<ParticleResponse> jsonResponse;

        try {
            String data = String.format(DATA_FORMAT, job.getLastBuild().getResult(), job.getName());
            L.debug("Publishing event with data: {}", data);

            ParticlePublish particlePublish = new ParticlePublish();
            particlePublish.setName(environment.getParticleName());
            particlePublish.setData(data);
            particlePublish.setTtl(environment.getParticleTtl());
            particlePublish.setPrivate(true);

            jsonResponse = Unirest.post(PARTICLE_API).
                header(HEADER_AUTHORIZATION, String.format(HEADER_AUTHORIZATION_VALUE, environment.getParticleToken())).
                header(HEADER_CONTENT_TYPE, HEADER_CONTENT_TYPE_VALUE).
                body(particlePublish).
                asObject(ParticleResponse.class);
        } catch (Exception e) {
            throw new JenkinsClientException("Failed to publish event to Particle Cloud API.", e);
        }

        return jsonResponse.getBody();
    }
}
