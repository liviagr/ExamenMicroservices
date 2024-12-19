package com.howtodoinjava.example.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class PatientController {
	
	@Autowired
    RestTemplate restTemplate;
 
    @RequestMapping(value = "/patientDetails/{patientid}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String getStudents(@PathVariable int patientid)
    {
        System.out.println("Getting patient details for " + patientid);
 
        String response = restTemplate.exchange("http://patient-service/findpatientDetails/{patientid}",
                                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, patientid).getBody();
 
        System.out.println("Response Body " + response);
 
        return "patient Id -  " + patientid + " [ patient Details " + response+" ]";
    }
    
    public String  fallbackMethod(int patientid){
    	
    	return "Fallback response:: No patient details available temporarily";
    }
 
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
