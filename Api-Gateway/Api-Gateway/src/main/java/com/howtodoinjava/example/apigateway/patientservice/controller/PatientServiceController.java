package com.howtodoinjava.example.apigateway.patientservice.controller;


import com.howtodoinjava.example.apigateway.patientservice.delegate.PatientServiceDelegate;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api(value = "Swagger2DemoRestController", description = "REST Apis related to Student Entity!!!!")
public class PatientServiceController {

    @Autowired
    PatientServiceDelegate patientServiceDelegate;

    // TODO REST : expose school + call student service to det data
    @GetMapping("/getPatients")
    public Map<String, String> getPatients() {
        return patientServiceDelegate.receiveAllPatients();
    }

    @GetMapping("/getPatientbyName/{name}")
    public Map<String, String> getPatientbyName(@PathVariable String name) {
        return patientServiceDelegate.receivePatientByName(name);
    }

    @GetMapping("/getPatient/{id}")
    public Map<String, String> getPatient(@PathVariable int id) {
        return patientServiceDelegate.receivePatient(id);
    }

    @PostMapping("/addPatient")
    public Map<String, String> createPatient(@RequestBody int id, String name) {
        return patientServiceDelegate.addPatient(id, name);
    }

    @PutMapping("/updatePatient/{id}")
    public Map<String, String> updatePatient(@PathVariable int id, @RequestBody String name) {
        return patientServiceDelegate.updatePatient(id, name);
    }

    @DeleteMapping("/deletePatient/{id}")
    public Map<String, String> deletePatient(@PathVariable int id) {
        return patientServiceDelegate.deletePatient(id);
    }
}
