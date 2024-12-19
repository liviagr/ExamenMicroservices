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
    @RequestMapping(value = "/getPatient/{name}")
    public Patient getPatient(@PathVariable(value = "name") String name) {
        // TODO return student
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

    @ApiOperation(value = "Get patient with the specified name in the System ", response = Patient.class, tags =
            "getPatient")
    @PostMapping("/addPatient")
    public Patient createPatient(@RequestBody Patient patient) {
        patientData.put(patient.getId(), patient);
        return patient;
    }

    @ApiOperation(value = "Get patient with the specified name in the System ", response = Patient.class, tags =
            "getPatient")
    @PutMapping("updatePatient/{id}")
    public Patient updatePatient(@PathVariable Integer id, @RequestBody Patient patient) {
        patientData.put(patient.getId(), patient);
        return patient;
    }

    @ApiOperation(value = "Get patient with the specified name in the System ", response = Patient.class, tags =
            "getPatient")
    @DeleteMapping("deletePatient/{id}")
    public void deletePerson(@PathVariable int id) {
        patientData.remove(id);
    }




}
