package com.ericschlenz.jenkinssync.client;

import com.ericschlenz.jenkinssync.Environment;
import com.ericschlenz.jenkinssync.domain.JobSet;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import javax.inject.Singleton;
import org.apache.commons.lang3.StringUtils;

@Singleton
public class JenkinsClientImpl implements JenkinsClient {
    private static final String API_JSON = "/api/json";
    private static final String KEY_TREE = "tree";
    private static final String VALUE_BASIC_JOB_INFO = "jobs[name,lastBuild[result]]";

    private final Environment environment;

    public JenkinsClientImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public JobSet getJobSet() {
        HttpResponse<JobSet> jsonResponse;

        try {
            GetRequest request = Unirest.get(getApiJsonUrl());

            if (!StringUtils.isEmpty(environment.getJenkinsUsername())) {
                request.basicAuth(environment.getJenkinsUsername(), environment.getJenkinsPassword());
            }

            jsonResponse = request.queryString(KEY_TREE, VALUE_BASIC_JOB_INFO).asObject(JobSet.class);
        } catch (UnirestException e) {
            throw new JenkinsClientException("Failed to request jobs from Jenkins API.", e);
        }

        return jsonResponse.getBody();
    }

    private String getApiJsonUrl() {
        return environment.getJenkinsUrl() + API_JSON;
    }
}
