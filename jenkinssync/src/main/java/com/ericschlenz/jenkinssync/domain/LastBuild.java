package com.ericschlenz.jenkinssync.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class LastBuild {
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
