package com.ericschlenz.jenkinssync.client;

public class ParticleClientException extends RuntimeException {
    public ParticleClientException() {
    }

    public ParticleClientException(String message) {
        super(message);
    }

    public ParticleClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParticleClientException(Throwable cause) {
        super(cause);
    }

    public ParticleClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
