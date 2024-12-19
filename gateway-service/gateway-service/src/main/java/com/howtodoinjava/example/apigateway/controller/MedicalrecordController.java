package com.howtodoinjava.example.apigateway.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

@RestController
public class MedicalrecordController {
	
	@Autowired
    RestTemplate restTemplate;
 
    @RequestMapping(value = "/medicalrecordDetails/{medicalrecordid}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String getStudents(@PathVariable int medicalrecordid)
    {
        System.out.println("Getting medicalrecord details for " + medicalrecordid);
 
        String response = restTemplate.exchange("http://medicalrecord-service/findmedicalrecordDetails/{medicalrecordid}",
                                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, medicalrecordid).getBody();
 
        System.out.println("Response Body " + response);
 
        return "medicalrecord Id -  " + medicalrecordid + " [ medicalrecord Details " + response+" ]";
    }
    
    public String  fallbackMethod(int medicalrecordid){
    	
    	return "Fallback response:: No medicalrecord details available temporarily";
    }
 
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
