package com.ericschlenz.jenkinssync.client;

import com.ericschlenz.jenkinssync.domain.JobSet;
import com.ericschlenz.jenkinssync.domain.ParticleResponse;

public interface ParticleClient {
    ParticleResponse publishEvent(JobSet jobSet);
}
