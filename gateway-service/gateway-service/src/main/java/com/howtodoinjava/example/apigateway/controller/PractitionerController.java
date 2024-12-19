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
public class PractitionerController {
	
	@Autowired
    RestTemplate restTemplate;
 
    @RequestMapping(value = "/practitionerDetails/{practitionerid}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String getStudents(@PathVariable int practitionerid)
    {
        System.out.println("Getting practitioner details for " + practitionerid);
 
        String response = restTemplate.exchange("http://practitioner-service/findpractitionerDetails/{practitionerid}",
                                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, practitionerid).getBody();
 
        System.out.println("Response Body " + response);
 
        return "practitioner Id -  " + practitionerid + " [ practitioner Details " + response+" ]";
    }
    
    public String  fallbackMethod(int practitionerid){
    	
    	return "Fallback response:: No practitioner details available temporarily";
    }
 
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
