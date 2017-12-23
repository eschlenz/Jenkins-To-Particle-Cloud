package com.ericschlenz.jenkinssync.domain;

import java.util.ArrayList;
import java.util.List;

public class JobSet {
    private List<Job> jobs = new ArrayList<>();

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
