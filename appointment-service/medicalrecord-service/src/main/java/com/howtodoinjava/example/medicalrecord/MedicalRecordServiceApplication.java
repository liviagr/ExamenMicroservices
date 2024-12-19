package com.howtodoinjava.example.medicalrecord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MedicalRecordServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalRecordServiceApplication.class, args);
	}
}
