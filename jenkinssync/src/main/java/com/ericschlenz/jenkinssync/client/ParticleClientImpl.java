package com.ericschlenz.jenkinssync.client;

import com.ericschlenz.jenkinssync.Environment;
import com.ericschlenz.jenkinssync.domain.*;
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

    private final Environment environment;

    public ParticleClientImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public ParticleResponse publishEvent(JobSet jobSet) {
        HttpResponse<ParticleResponse> jsonResponse;
        ArduinoUpdate arduinoUpdate = createArduinoUpdate(jobSet);

        try {
            String formattedStatus = arduinoUpdate.getFormattedStatuses();
            L.debug("Publishing event with data: {}", formattedStatus);

            ParticlePublish particlePublish = new ParticlePublish();
            particlePublish.setName(environment.getParticleName());
            particlePublish.setData(formattedStatus);
            particlePublish.setTtl(environment.getParticleTtl());
            particlePublish.setPrivate(true);

            jsonResponse = Unirest.post(PARTICLE_API).
                header(HEADER_AUTHORIZATION, String.format(HEADER_AUTHORIZATION_VALUE, environment.getParticleToken())).
                header(HEADER_CONTENT_TYPE, HEADER_CONTENT_TYPE_VALUE).
                body(particlePublish).
                asObject(ParticleResponse.class);
        } catch (Exception e) {
            throw new ParticleClientException("Failed to publish event to Particle Cloud API.", e);
        }

        return jsonResponse.getBody();
    }

    private ArduinoUpdate createArduinoUpdate(JobSet jobSet) {
        ArduinoUpdate arduinoUpdate = new ArduinoUpdate();

        for (Job job : jobSet.getJobs()) {
            if (job == null) {
                L.warn("One or more jobs are null.");
                continue;
            }

            L.debug("Updating data for job: {}", job);

            LastBuild lastBuild = job.getLastBuild();

            if (lastBuild == null) {
                lastBuild = LastBuild.UNKNOWN;
            }

            arduinoUpdate.update(lastBuild);
        }

        return arduinoUpdate;
    }
}
