package com.ericschlenz.jenkinssync.client;

import com.ericschlenz.jenkinssync.domain.JobSet;

public interface JenkinsClient {
    JobSet getJobSet();
}
