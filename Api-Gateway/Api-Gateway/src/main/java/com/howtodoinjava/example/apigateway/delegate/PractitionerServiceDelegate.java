package com.howtodoinjava.example.apigateway.delegate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PractitionerServiceDelegate {

    @Autowired
    RestTemplate restTemplate;

    // TODO call our student service with details : make sure to do http exchange method : call fallback method if my service is down
    @HystrixCommand(fallbackMethod = "fallbackResponsePractitioner")
    public Map<String, String> receiveAllPractitioners() {
        String call = this.restTemplate.exchange(
                "http://localhost:8012/getPractitioners",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                }).getBody();
        Map<String, String> response = new HashMap<>();
        response.put("received", call);
        return response;
    }

    @HystrixCommand(fallbackMethod = "fallbackResponsePractitioner")
    public Map<String, String> receivePractitionerByName(String name) {
        String call = this.restTemplate.exchange(
                "http://localhost:8012/getpractitionerbyName/{name}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                }, name).getBody();
        Map<String, String> response = new HashMap<>();
        response.put("received", call);
        return response;
    }
    @HystrixCommand(fallbackMethod = "fallbackResponsePractitioner")
    public Map<String, String> receivePractitioner(int id) {
        String call = this.restTemplate.exchange(
                "http://localhost:8012/getPractitioner/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                }, id).getBody();
        Map<String, String> response = new HashMap<>();
        response.put("received", call);
        return response;
    }

    @HystrixCommand(fallbackMethod = "fallbackResponseAddPractitioner")
    public Map<String, String> addPractitioner(int id, String name) {
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);
        requestParams.put("name", name);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestParams);

        String call = this.restTemplate.exchange(
                "http://localhost:8012/addPractitioner",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<String>() {
                }).getBody();
        Map<String, String> response = new HashMap<>();
        response.put("received", call);
        return response;
    }

    @HystrixCommand(fallbackMethod = "fallbackResponseUpdatePractitioner")
    public Map<String, String> updatePractitioner(int id, String name) {
        HttpEntity<String> request = new HttpEntity<>(name);
        String call = this.restTemplate.exchange(
                "http://localhost:8012/updatePractitioner/{id}",
                HttpMethod.PUT,
                request,
                new ParameterizedTypeReference<String>() {
                }, id).getBody();
        Map<String, String> response = new HashMap<>();
        response.put("received", call);
        return response;
    }

    @HystrixCommand(fallbackMethod = "fallbackResponsePractitioner")
    public Map<String, String> deletePractitioner(int id) {
        String call = this.restTemplate.exchange(
                "http://localhost:8012/deletePractitioner/{id}",
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<String>() {
                }, id).getBody();
        Map<String, String> response = new HashMap<>();
        response.put("received", call);
        return response;
    }



    public Map<String, String> fallbackResponsePractitioner(){
        Map<String, String> response = new HashMap<>();
        response.put("received", "Aucune information disponible");
        return response;
    }

    public Map<String, String> fallbackResponsePractitioner(String name){
        Map<String, String> response = new HashMap<>();
        response.put("received", "Aucune information disponible pour le Practitioner portant ce nom");
        return response;
    }

    public Map<String, String> fallbackResponsePractitioner(int id){
        Map<String, String> response = new HashMap<>();
        response.put("received", "Aucune information disponible pour le Practitioner correspondant à l'id donné");
        return response;
    }

    public Map<String, String> fallbackResponseAddPractitioner(int id, String name){
        Map<String, String> response = new HashMap<>();
        response.put("received", "Le Practitioner n'a pas pu être ajouté");
        return response;
    }

    public Map<String, String> fallbackResponseUpdatePractitioner(int id, String name){
        Map<String, String> response = new HashMap<>();
        response.put("received", "Le Practitioner n'a pas pu être modifié");
        return response;
    }
}
