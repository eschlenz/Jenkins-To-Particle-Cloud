package com.ericschlenz.jenkinssync.di;

import com.ericschlenz.jenkinssync.App;
import com.ericschlenz.jenkinssync.Environment;
import com.ericschlenz.jenkinssync.client.UnirestObjectMapper;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by Eric on 12/23/17.
 */
@Singleton
@Component(modules = { MainModule.class })
public interface MainComponent {
    Environment getEnvironment();
    App getApp();
    UnirestObjectMapper getUnirestObjectMapper();
}
