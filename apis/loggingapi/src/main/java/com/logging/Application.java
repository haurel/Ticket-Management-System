package com.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.configuration.CommonApiSecurityConfig;
import com.configuration.CommonApiSwaggerConfiguration;

@SpringBootApplication
@Import({CommonApiSwaggerConfiguration.class, CommonApiSecurityConfig.class })
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
