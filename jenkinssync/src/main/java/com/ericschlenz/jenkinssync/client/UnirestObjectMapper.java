package com.ericschlenz.jenkinssync.client;

import com.ericschlenz.jenkinssync.json.JacksonHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import java.io.IOException;
import javax.inject.Singleton;

@Singleton
public class UnirestObjectMapper implements ObjectMapper {
    private final JacksonHelper jacksonHelper;

    public UnirestObjectMapper(JacksonHelper jacksonHelper) {
        this.jacksonHelper = jacksonHelper;
    }

    public <T> T readValue(String value, Class<T> valueType) {
        try {
            return jacksonHelper.getObjectMapper().readValue(value, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String writeValue(Object value) {
        try {
            return jacksonHelper.getObjectMapper().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
