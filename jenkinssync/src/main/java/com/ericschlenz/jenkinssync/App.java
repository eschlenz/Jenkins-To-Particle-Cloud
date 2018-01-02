package com.ericschlenz.jenkinssync;

import com.ericschlenz.jenkinssync.client.JenkinsClient;
import com.ericschlenz.jenkinssync.client.JenkinsClientException;
import com.ericschlenz.jenkinssync.client.ParticleClient;
import com.ericschlenz.jenkinssync.client.ParticleClientException;
import com.ericschlenz.jenkinssync.domain.Job;
import com.ericschlenz.jenkinssync.domain.JobSet;
import javax.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class App {
    private static final Logger L = LogManager.getLogger(App.class);

    private final JenkinsClient jenkinsClient;
    private final ParticleClient particleClient;

    public App(JenkinsClient jenkinsClient, ParticleClient particleClient) {
        this.jenkinsClient = jenkinsClient;
        this.particleClient = particleClient;
    }

    public void start() {
        JobSet jobSet;

        try {
            jobSet = jenkinsClient.getJobSet();
            L.debug("Found {} jobs.", jobSet.getJobs().size());
        } catch (JenkinsClientException e) {
            L.error("Failed to fetch Jenkins jobs.", e);
            return;
        }

        try {
            L.debug("Response from Particle Cloud API: {}", particleClient.publishEvent(jobSet));
        } catch (ParticleClientException e) {
            L.error("Failed to post update to Particle Cloud API.", e);
        }
    }
}
