package com.tms.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import com.configuration.CommonBaseBeanConfigurations;
import com.configuration.CommonProperties;

@SpringBootApplication
@EnableConfigurationProperties(CommonProperties.class)
@Import(CommonBaseBeanConfigurations.class)
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
}
