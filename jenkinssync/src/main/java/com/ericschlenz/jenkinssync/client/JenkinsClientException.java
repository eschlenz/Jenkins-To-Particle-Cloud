package com.ericschlenz.jenkinssync.client;

public class JenkinsClientException extends RuntimeException {
    public JenkinsClientException() {
    }

    public JenkinsClientException(String message) {
        super(message);
    }

    public JenkinsClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public JenkinsClientException(Throwable cause) {
        super(cause);
    }

    public JenkinsClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
