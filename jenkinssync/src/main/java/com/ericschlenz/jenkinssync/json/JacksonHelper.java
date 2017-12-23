package com.ericschlenz.jenkinssync.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import java.io.IOException;
import javax.inject.Singleton;

@Singleton
public class JacksonHelper {
    private final ObjectMapper objMapper;

    public JacksonHelper() {
        this.objMapper = new ObjectMapper();

        objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        DefaultSerializerProvider sp = new DefaultSerializerProvider.Impl();
        sp.setNullValueSerializer(NullSerializer.instance);

        objMapper.setSerializerProvider(sp);
        objMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public ObjectMapper getObjectMapper() {
        return objMapper;
    }

    public <R, C extends Class<R>> R fromJson(String jsonStr, C type) throws IOException {
        return objMapper.reader(type).readValue(jsonStr);
    }

    public String toJson(Object obj) throws JsonProcessingException {
        return objMapper.writer().writeValueAsString(obj);
    }
}
