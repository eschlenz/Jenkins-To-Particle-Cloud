package com.ericschlenz.jenkinssync.di;

import com.ericschlenz.jenkinssync.App;
import com.ericschlenz.jenkinssync.Environment;
import com.ericschlenz.jenkinssync.client.*;
import com.ericschlenz.jenkinssync.json.JacksonHelper;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Eric on 12/23/17.
 */
@Module
public class MainModule {
    @Provides @Singleton
    public Environment provideEnvironment() {
        return new Environment();
    }

    @Provides @Singleton
    public App provideApp(JenkinsClient jenkinsClient, ParticleClient particleClient) {
        return new App(jenkinsClient, particleClient);
    }

    @Provides @Singleton
    public JacksonHelper provideJacksonHelper() {
        return new JacksonHelper();
    }

    @Provides @Singleton
    public UnirestObjectMapper provideUnirestObjectMapper(JacksonHelper jacksonHelper) {
        return new UnirestObjectMapper(jacksonHelper);
    }

    @Provides @Singleton
    public JenkinsClient provideJenkinsClient(Environment environment) {
        return new JenkinsClientImpl(environment);
    }

    @Provides @Singleton
    public ParticleClient provideParticleClient(Environment environment) {
        return new ParticleClientImpl(environment);
    }
}
