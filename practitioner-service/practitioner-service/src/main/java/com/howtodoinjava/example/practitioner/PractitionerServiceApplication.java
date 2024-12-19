package com.howtodoinjava.example.practitioner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PractitionerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PractitionerServiceApplication.class, args);
	}
}
