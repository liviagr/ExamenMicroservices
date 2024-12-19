package com.howtodoinjava.example.apigateway.controller;


import com.howtodoinjava.example.apigateway.delegate.PatientServiceDelegate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api(value = "PatientServiceController", description = "REST Apis related to Patient Entity")
public class PatientServiceController {

    @Autowired
    PatientServiceDelegate patientServiceDelegate;

    @ApiOperation(value = "Get list of patients in the System ", response = Iterable.class, tags = "getPatients")
    @GetMapping("/getPatients")
    public Map<String, String> getPatients() {
        return patientServiceDelegate.receiveAllPatients();
    }

    @ApiOperation(value = "Get patient with the specified name in the System ", response = Iterable.class, tags =
            "getPatient")
    @GetMapping("/getPatientbyName/{name}")
    public Map<String, String> getPatientbyName(@PathVariable String name) {
        return patientServiceDelegate.receivePatientByName(name);
    }

    @ApiOperation(value = "Get patient with the specified id in the System ", response = Iterable.class, tags =
            "getPatient")
    @GetMapping("/getPatient/{id}")
    public Map<String, String> getPatient(@PathVariable int id) {
        return patientServiceDelegate.receivePatient(id);
    }

    @ApiOperation(value = "Add a patient", response = Iterable.class, tags =
            "addPatient")
    @PostMapping("/addPatient")
    public Map<String, String> createPatient(@RequestBody int id, String name) {
        return patientServiceDelegate.addPatient(id, name);
    }

    @ApiOperation(value = "Update a patient", response = Iterable.class, tags =
            "updatePatient")
    @PutMapping("/updatePatient/{id}")
    public Map<String, String> updatePatient(@PathVariable int id, @RequestBody String name) {
        return patientServiceDelegate.updatePatient(id, name);
    }

    @ApiOperation(value = "Delete the specified patient", response = Iterable.class, tags =
            "deletePatient")
    @DeleteMapping("/deletePatient/{id}")
    public Map<String, String> deletePatient(@PathVariable int id) {
        return patientServiceDelegate.deletePatient(id);
    }
}
