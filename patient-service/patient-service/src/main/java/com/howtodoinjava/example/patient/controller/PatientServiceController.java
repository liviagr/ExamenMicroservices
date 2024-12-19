package com.howtodoinjava.example.patient.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import com.howtodoinjava.example.patient.beans.Patient;

@RestController
//@RequestMapping(value = "patient/")
@Api(value = "Swagger2DemoRestController", description = "REST Apis related to Student Entity!!!!")
public class PatientServiceController {
	
	
	private static final Map<Integer, Patient> patientData = new HashMap<Integer, Patient>() {
		/**
		 * 
		 */

		{
			put(111,new Patient(111, "patient1"));
			put(222,new Patient(222, "patient2"));
		}
 
    };

    @ApiOperation(value = "Get list of patients in the System ", response = Iterable.class, tags = "getPatients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @RequestMapping(value = "/getPatients")
    public Map<Integer, Patient> getPatients(){
        return patientData;
    }


    @ApiOperation(value = "Get patient with the specified name in the System ", response = Patient.class, tags =
            "getPatient")
    @RequestMapping(value = "/getPatientbyName/{name}")
    public Patient getPatient(@PathVariable(value = "name") String name) {
        for (Patient patient : patientData.values()) {
            if(patient.getName().equals((name))){
                return patient;
            }
        }
        return null;
    }

    @ApiOperation(value = "Get patient with the specified name in the System ", response = Patient.class, tags =
            "getPatient")
    @RequestMapping(value = "/getPatient/{patientId}", method = RequestMethod.GET)
    public Patient getpatientDetails(@PathVariable int patientId) {
        System.out.println("Getting patient details for " + patientId);
 
        Patient patient = patientData.get(patientId);
        if (patient == null) {
            
        	patient = new Patient(0, "N/A");
            
        }
        return patient;
    }

    @ApiOperation(value = "Add a patient", response = Patient.class, tags =
            "addPatient")
    @PostMapping("/addPatient")
    public Map<Integer, Patient> createPatient(@RequestBody int id, String name) {
        patientData.put(id, new Patient(id, name));
        return patientData;
    }

    @ApiOperation(value = "Update a patient", response = Patient.class, tags =
            "updatePatient")
    @PutMapping("updatePatient/{id}")
    public Patient updatePatient(@PathVariable int id, @RequestBody String name) {
        patientData.get(id).setName(name);
        patientData.put(id, patientData.get(id));
        return patientData.get(id);
    }

    @ApiOperation(value = "Delete the specified patient", response = Patient.class, tags =
            "deletePatient")
    @DeleteMapping("deletePatient/{id}")
    public void deletePerson(@PathVariable int id) {
        patientData.remove(id);
    }




}
