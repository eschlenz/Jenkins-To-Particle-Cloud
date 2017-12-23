package com.ericschlenz.jenkinssync.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ParticleResponse {
    private boolean ok;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
