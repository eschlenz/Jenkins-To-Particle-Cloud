package com.ericschlenz.jenkinssync.client;

public class JenkinsClientException extends RuntimeException {
    public JenkinsClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
