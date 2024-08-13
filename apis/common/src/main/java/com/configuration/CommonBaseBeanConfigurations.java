package com.configuration;

import java.net.http.HttpClient;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.common.repository.logging.LoggingApiRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class CommonBaseBeanConfigurations {
    
    @Bean
    CommonProperties commonProperties() {
        return new CommonProperties();
    }
    
    @Bean
    HttpClient httpClient() {
        return HttpClient.newBuilder().build();
    }

    @Bean
    ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return objectMapper;
    }

    @Bean
    LoggingApiRepository loggingApiRepository() {
        return new LoggingApiRepository(httpClient(), objectMapper(), commonProperties());
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
