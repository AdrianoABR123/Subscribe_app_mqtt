package com.ecosniff.SubscribeApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class SubscribeAppApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SubscribeAppApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}
}
