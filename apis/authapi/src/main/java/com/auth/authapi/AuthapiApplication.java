package com.auth.authapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.configuration.CommonApiSecurityConfig;
import com.configuration.CommonApiSwaggerConfiguration;

@SpringBootApplication
@Import({CommonApiSwaggerConfiguration.class, CommonApiSecurityConfig.class })
public class AuthapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthapiApplication.class, args);
	}

}
