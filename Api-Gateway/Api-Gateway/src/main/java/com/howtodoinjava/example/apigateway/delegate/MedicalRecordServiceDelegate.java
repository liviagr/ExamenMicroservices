package com.howtodoinjava.example.apigateway.delegate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class MedicalRecordServiceDelegate {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackResponseMedicalRecord")
    public Map<String, String> receiveAllMedicalRecords() {
        String call = this.restTemplate.exchange(
                "http://localhost:8013/getMedicalRecords",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                }).getBody();
        Map<String, String> response = new HashMap<>();
        response.put("received", call);
        return response;
    }


    @HystrixCommand(fallbackMethod = "fallbackResponseMedicalRecord")
    public Map<String, String> receiveMedicalRecord(int id) {
        String call = this.restTemplate.exchange(
                "http://localhost:8011/getMedicalRecord/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                }, id).getBody();
        Map<String, String> response = new HashMap<>();
        response.put("received", call);
        return response;
    }

    @HystrixCommand(fallbackMethod = "fallbackResponseMedicalRecord")
    public Map<String, String> receiveMedicalRecordFromPatient(int id) {
        String call = this.restTemplate.exchange(
                "http://localhost:8013/getmedicalrecordfrompatient/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                }, id).getBody();
        Map<String, String> response = new HashMap<>();
        response.put("received", call);
        return response;
    }

    @HystrixCommand(fallbackMethod = "fallbackResponseMedicalRecord")
    public Map<String, String> receiveMedicalRecordFromPractitioner(int id) {
        String call = this.restTemplate.exchange(
                "http://localhost:8013/getmedicalrecordfrompractitioner/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                }, id).getBody();
        Map<String, String> response = new HashMap<>();
        response.put("received", call);
        return response;
    }

    @HystrixCommand(fallbackMethod = "fallbackResponseAddMedicalRecord")
    public Map<String, String> addMedicalRecord(@RequestBody int id, int patientId, int practitionerId) {
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);
        requestParams.put("patientId", patientId);
        requestParams.put("practitionerId", practitionerId);


        HttpEntity request = new HttpEntity<>(requestParams);

        String call = this.restTemplate.exchange(
                "http://localhost:8013/addMedicalRecord",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<String>() {
                }).getBody();
        Map<String, String> response = new HashMap<>();
        response.put("received", call);
        return response;
    }

    @HystrixCommand(fallbackMethod = "fallbackResponseUpdateMedicalRecord")
    public Map<String, String> updateMedicalRecord(@PathVariable int id, @RequestBody int patientId, int practitionerId) {
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("patientId", patientId);
        requestParams.put("practitionerId", practitionerId);


        HttpEntity request = new HttpEntity<>(requestParams);
        String call = this.restTemplate.exchange(
                "http://localhost:8013/updateMedicalRecord/{id}",
                HttpMethod.PUT,
                request,
                new ParameterizedTypeReference<String>() {
                }, id).getBody();
        Map<String, String> response = new HashMap<>();
        response.put("received", call);
        return response;
    }

    @HystrixCommand(fallbackMethod = "fallbackResponseMedicalRecord")
    public Map<String, String> deleteMedicalRecord(int id) {
        String call = this.restTemplate.exchange(
                "http://localhost:8013/deleteMedicalRecord/{id}",
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<String>() {
                }, id).getBody();
        Map<String, String> response = new HashMap<>();
        response.put("received", call);
        return response;
    }



    public Map<String, String> fallbackResponseMedicalRecord(){
        Map<String, String> response = new HashMap<>();
        response.put("received", "Aucune information disponible");
        return response;
    }

    public Map<String, String> fallbackResponseMedicalRecord(String name){
        Map<String, String> response = new HashMap<>();
        response.put("received", "Aucune information disponible pour le MedicalRecord portant ce nom");
        return response;
    }

    public Map<String, String> fallbackResponseMedicalRecord(int id){
        Map<String, String> response = new HashMap<>();
        response.put("received", "Aucune information disponible pour le MedicalRecord correspondant à l'id donné");
        return response;
    }

    public Map<String, String> fallbackResponseAddMedicalRecord(int id,  int patientId, int practitionerId){
        Map<String, String> response = new HashMap<>();
        response.put("received", "Le MedicalRecord n'a pas pu être ajouté");
        return response;
    }

    public Map<String, String> fallbackResponseUpdateMedicalRecord(int id,  int patientId, int practitionerId){
        Map<String, String> response = new HashMap<>();
        response.put("received", "Le MedicalRecord n'a pas pu être modifié");
        return response;
    }

}
