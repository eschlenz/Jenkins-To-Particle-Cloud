package com.ericschlenz.jenkinssync.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Job {
    private String name;
    private LastBuild lastBuild;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LastBuild getLastBuild() {
        return lastBuild;
    }

    public void setLastBuild(LastBuild lastBuild) {
        this.lastBuild = lastBuild;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
