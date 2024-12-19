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
public class AppointmentController {
	
	@Autowired
    RestTemplate restTemplate;
 
    @RequestMapping(value = "/appointmentDetails/{appointmentid}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String getStudents(@PathVariable int appointmentid)
    {
        System.out.println("Getting appointment details for " + appointmentid);
 
        String response = restTemplate.exchange("http://appointment-service/findappointmentDetails/{appointmentid}",
                                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, appointmentid).getBody();
 
        System.out.println("Response Body " + response);
 
        return "appointment Id -  " + appointmentid + " [ appointment Details " + response+" ]";
    }
    
    public String  fallbackMethod(int appointmentid){
    	
    	return "Fallback response:: No appointment details available temporarily";
    }
 
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
