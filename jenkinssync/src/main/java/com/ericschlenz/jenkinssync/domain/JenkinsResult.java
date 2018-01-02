package com.ericschlenz.jenkinssync.domain;

public enum JenkinsResult {
    SUCCESS,
    FAILURE,
    UNSTABLE,
    ABORTED,
    NOT_BUILT,
    NULL;

    public static JenkinsResult fromString(String status) {
        JenkinsResult jenkinsResult;

        try {
            jenkinsResult = JenkinsResult.valueOf(status);
        } catch (Exception e) {
            jenkinsResult = JenkinsResult.NULL;
            // Eat it.
        }

        return jenkinsResult;
    }
}
