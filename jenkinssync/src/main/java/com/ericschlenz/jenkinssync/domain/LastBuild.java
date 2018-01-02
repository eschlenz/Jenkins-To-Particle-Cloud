package com.ericschlenz.jenkinssync.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class LastBuild {
    public static final LastBuild UNKNOWN;

    static {
        UNKNOWN = new LastBuild();
        UNKNOWN.setResult("NULL");
    }

    private String result;

    public String getResult() {
        return result;
    }

    public JenkinsResult getResultEnum() {
        return JenkinsResult.fromString(result);
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
