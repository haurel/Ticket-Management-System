package com.auth.authapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import com.configuration.CommonApiSecurityConfig;
import com.configuration.CommonApiSwaggerConfiguration;
import com.configuration.CommonBaseBeanConfigurations;
import com.configuration.CommonProperties;

@SpringBootApplication
@EnableConfigurationProperties(CommonProperties.class)
@Import({CommonBaseBeanConfigurations.class, CommonApiSwaggerConfiguration.class, CommonApiSecurityConfig.class })
public class AuthapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthapiApplication.class, args);
	}

}
