package com.ericschlenz.jenkinssync.domain;

public class ArduinoUpdate {
    private static final String FORMAT = "%03d:%03d:%03d:%03d:%03d:%03d";
    private int success;
    private int failure;
    private int unstable;
    private int aborted;
    private int notBuilt;
    private int unknown;

    public void update(LastBuild lastBuild) {
        switch (lastBuild.getResultEnum()) {
            case SUCCESS:
                success++;
                break;
            case FAILURE:
                failure++;
                break;
            case UNSTABLE:
                unstable++;
                break;
            case ABORTED:
                aborted++;
                break;
            case NOT_BUILT:
                notBuilt++;
                break;
            case NULL:
                unknown++;
                break;
        }
    }

    public String getFormattedStatuses() {
        return String.format(FORMAT, success, failure, unstable, aborted, notBuilt, unknown);
    }
}
