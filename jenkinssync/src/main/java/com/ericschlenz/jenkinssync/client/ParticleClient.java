package com.ericschlenz.jenkinssync.client;

import com.ericschlenz.jenkinssync.domain.Job;
import com.ericschlenz.jenkinssync.domain.ParticleResponse;

public interface ParticleClient {
    ParticleResponse publishEvent(Job job);
}
