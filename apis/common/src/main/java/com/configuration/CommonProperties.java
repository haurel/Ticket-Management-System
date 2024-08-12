package com.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "common")
@PropertySource("classpath:common.properties")
public class CommonProperties {
    String loggingApiUrl;
    String apiToken;
}
