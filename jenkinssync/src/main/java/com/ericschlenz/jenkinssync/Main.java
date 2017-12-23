package com.ericschlenz.jenkinssync;

import com.ericschlenz.jenkinssync.di.DaggerMainComponent;
import com.ericschlenz.jenkinssync.di.MainComponent;
import com.mashape.unirest.http.Unirest;

public class Main {
    public static void main(String[] args) {
        MainComponent mainComponent = DaggerMainComponent.builder().build();
        Unirest.setObjectMapper(mainComponent.getUnirestObjectMapper());
        mainComponent.getEnvironment().initialize(args);
        mainComponent.getApp().start();
    }
}
