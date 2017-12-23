package com.ericschlenz.jenkinssync.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParticlePublish {
    private String name;
    private String data;
    private String ttl;
    @JsonProperty("private")
    private boolean isPrivate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
