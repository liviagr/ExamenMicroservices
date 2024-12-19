package com.howtodoinjava.example.apigateway.delegate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PatientServiceDelegate {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackResponsePatient")
    public Map<String, String> receiveAllPatients() {
        String call = this.restTemplate.exchange(
                "http://localhost:8011/getPatients",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                }).getBody();
        Map<String, String> response = new HashMap<>();
        response.put("received", call);
        return response;
    }

    @HystrixCommand(fallbackMethod = "fallbackResponsePatient")
    public Map<String, String> receivePatientByName(String name) {
        String call = this.restTemplate.exchange(
                "http://localhost:8011/getPatientbyName/{name}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                }, name).getBody();
        Map<String, String> response = new HashMap<>();
        response.put("received", call);
        return response;
    }
    @HystrixCommand(fallbackMethod = "fallbackResponsePatient")
    public Map<String, String> receivePatient(int id) {
        String call = this.restTemplate.exchange(
                "http://localhost:8011/getPatient/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                }, id).getBody();
        Map<String, String> response = new HashMap<>();
        response.put("received", call);
        return response;
    }

    @HystrixCommand(fallbackMethod = "fallbackResponseAddPatient")
    public Map<String, String> addPatient(int id, String name) {
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);
        requestParams.put("name", name);

        HttpEntity request = new HttpEntity<>(requestParams);

        String call = this.restTemplate.exchange(
                "http://localhost:8011/addPatient",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<String>() {
                }).getBody();
        Map<String, String> response = new HashMap<>();
        response.put("received", call);
        return response;
    }

    @HystrixCommand(fallbackMethod = "fallbackResponseUpdatePatient")
    public Map<String, String> updatePatient(int id, String name) {
        HttpEntity<String> request = new HttpEntity<>(name);
        String call = this.restTemplate.exchange(
                "http://localhost:8011/updatePatient/{id}",
                HttpMethod.PUT,
                request,
                new ParameterizedTypeReference<String>() {
                }, id).getBody();
        Map<String, String> response = new HashMap<>();
        response.put("received", call);
        return response;
    }

    @HystrixCommand(fallbackMethod = "fallbackResponsePatient")
    public Map<String, String> deletePatient(int id) {
        String call = this.restTemplate.exchange(
                "http://localhost:8011/deletePatient/{id}",
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<String>() {
                }, id).getBody();
        Map<String, String> response = new HashMap<>();
        response.put("received", call);
        return response;
    }



    public Map<String, String> fallbackResponsePatient(){
        Map<String, String> response = new HashMap<>();
        response.put("received", "Aucune information disponible");
        return response;
    }

    public Map<String, String> fallbackResponsePatient(String name){
        Map<String, String> response = new HashMap<>();
        response.put("received", "Aucune information disponible pour le patient portant ce nom");
        return response;
    }

    public Map<String, String> fallbackResponsePatient(int id){
        Map<String, String> response = new HashMap<>();
        response.put("received", "Aucune information disponible pour le patient correspondant à l'id donné");
        return response;
    }

    public Map<String, String> fallbackResponseAddPatient(int id, String name){
        Map<String, String> response = new HashMap<>();
        response.put("received", "Le patient n'a pas pu être ajouté");
        return response;
    }

    public Map<String, String> fallbackResponseUpdatePatient(int id, String name){
        Map<String, String> response = new HashMap<>();
        response.put("received", "Le patient n'a pas pu être modifié");
        return response;
    }

}
