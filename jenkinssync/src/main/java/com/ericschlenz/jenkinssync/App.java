package com.ericschlenz.jenkinssync;

import com.ericschlenz.jenkinssync.client.JenkinsClient;
import com.ericschlenz.jenkinssync.client.ParticleClient;
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
        JobSet jobSet = jenkinsClient.getJobSet();
        L.debug("Found {} jobs.", jobSet.getJobs().size());

        for (int i=0; i < jobSet.getJobs().size(); i++) {
            Job job = jobSet.getJobs().get(i);
            L.debug("Publishing job status to Particle Cloud API #{}: {}", i, job);
            L.debug("Response from Particle Cloud API: {}", particleClient.publishEvent(jobSet.getJobs().get(i)));
        }
    }
}
