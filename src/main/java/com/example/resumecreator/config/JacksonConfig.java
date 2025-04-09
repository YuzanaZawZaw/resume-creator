package com.example.resumecreator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.StreamWriteConstraints;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfig {
@Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.getFactory().setStreamWriteConstraints(
            StreamWriteConstraints.builder().maxNestingDepth(2000).build()
        );
        return mapper;
    }
}
