package com.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
	    exclude = {
	        org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.class
	    }
	)
	public class ApiGatewayApplication {
	    public static void main(String[] args) {
	        SpringApplication.run(ApiGatewayApplication.class, args);
	    }
	}
