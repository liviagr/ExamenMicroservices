package com.howtodoinjava.example.apigateway.controller;


import com.howtodoinjava.example.apigateway.delegate.MedicalRecordServiceDelegate;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api(value = "Swagger2DemoRestController", description = "REST Apis related to Student Entity!!!!")
public class MedicalRecordServiceController {

    @Autowired
    MedicalRecordServiceDelegate medicalRecordServiceDelegate;

    // TODO REST : expose school + call student service to det data
    @GetMapping("/getMedicalRecords")
    public Map<String, String> getMedicalRecords() {
        return medicalRecordServiceDelegate.receiveAllMedicalRecords();
    }


    @GetMapping("/getMedicalRecord/{id}")
    public Map<String, String> getMedicalRecord(@PathVariable int id) {
        return medicalRecordServiceDelegate.receiveMedicalRecord(id);
    }

    @GetMapping("/getmedicalrecordfrompatient/{id}")
    public Map<String, String> getmedicalrecordfrompatient(@PathVariable int id) {
        return medicalRecordServiceDelegate.receiveMedicalRecordFromPatient(id);
    }

    @GetMapping("/getmedicalrecordfrompractitioner/{id}")
    public Map<String, String> getmedicalrecordfrompractitioner(@PathVariable int id) {
        return medicalRecordServiceDelegate.receiveMedicalRecordFromPractitioner(id);
    }



    @PostMapping("/addMedicalRecord")
    public Map<String, String> createMedicalRecord(@RequestBody int id, int patientId, int practitionerId) {
        return medicalRecordServiceDelegate.addMedicalRecord(id, patientId, practitionerId);
    }

    @PutMapping("/updateMedicalRecord/{id}")
    public Map<String, String> updateMedicalRecord(@PathVariable int id, @RequestBody int patientId, int practitionerId) {
        return medicalRecordServiceDelegate.updateMedicalRecord(id, patientId, practitionerId);
    }

    @DeleteMapping("/deleteMedicalRecord/{id}")
    public Map<String, String> deleteMedicalRecord(@PathVariable int id) {
        return medicalRecordServiceDelegate.deleteMedicalRecord(id);
    }
}
