package com.howtodoinjava.example.apigateway.controller;


import com.howtodoinjava.example.apigateway.delegate.MedicalRecordServiceDelegate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api(value = "MedicalRecordServiceController", description = "REST Apis related to Medical Records")
public class MedicalRecordServiceController {

    @Autowired
    MedicalRecordServiceDelegate medicalRecordServiceDelegate;

    @ApiOperation(value = "Get list of medicalrecords in the System ", response = Iterable.class, tags = "getmedicalrecords")
    @GetMapping("/getMedicalRecords")
    public Map<String, String> getMedicalRecords() {
        return medicalRecordServiceDelegate.receiveAllMedicalRecords();
    }

    @ApiOperation(value = "Get medicalrecord with the specified id in the System ", response = Iterable.class,
            tags =
                    "getmedicalrecord")
    @GetMapping("/getMedicalRecord/{id}")
    public Map<String, String> getMedicalRecord(@PathVariable int id) {
        return medicalRecordServiceDelegate.receiveMedicalRecord(id);
    }
    @ApiOperation(value = "Get medicalrecord of the specified patient in the System ", response =
            Iterable.class,
            tags =
                    "getmedicalrecordfrompatient")
    @GetMapping("/getmedicalrecordfrompatient/{id}")
    public Map<String, String> getmedicalrecordfrompatient(@PathVariable int id) {
        return medicalRecordServiceDelegate.receiveMedicalRecordFromPatient(id);
    }
    @ApiOperation(value = "Get medicalrecord of the specified practitioner in the System ", response =
            Iterable.class,
            tags =
                    "getmedicalrecordfrompractitioner")
    @GetMapping("/getmedicalrecordfrompractitioner/{id}")
    public Map<String, String> getmedicalrecordfrompractitioner(@PathVariable int id) {
        return medicalRecordServiceDelegate.receiveMedicalRecordFromPractitioner(id);
    }

    @ApiOperation(value = "Add a medicalrecord", response = Iterable.class, tags =
            "addmedicalrecord")
    @PostMapping("/addMedicalRecord")
    public Map<String, String> createMedicalRecord(@RequestBody int id, int patientId, int practitionerId) {
        return medicalRecordServiceDelegate.addMedicalRecord(id, patientId, practitionerId);
    }
    @ApiOperation(value = "Update a medicalrecord", response = Iterable.class, tags =
            "updatemedicalrecord")
    @PutMapping("/updateMedicalRecord/{id}")
    public Map<String, String> updateMedicalRecord(@PathVariable int id, @RequestBody int patientId, int practitionerId) {
        return medicalRecordServiceDelegate.updateMedicalRecord(id, patientId, practitionerId);
    }
    @ApiOperation(value = "Delete the specified medicalrecord", response = Iterable.class, tags =
            "deletemedicalrecord")
    @DeleteMapping("/deleteMedicalRecord/{id}")
    public Map<String, String> deleteMedicalRecord(@PathVariable int id) {
        return medicalRecordServiceDelegate.deleteMedicalRecord(id);
    }
}
