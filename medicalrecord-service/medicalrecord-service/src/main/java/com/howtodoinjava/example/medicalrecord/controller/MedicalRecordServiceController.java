package com.howtodoinjava.example.medicalrecord.controller;

import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import com.howtodoinjava.example.medicalrecord.beans.MedicalRecord;

@RestController
//@RequestMapping(value = "medicalrecord/")
@Api(value = "Swagger2DemoRestController", description = "REST Apis related to Student Entity!!!!")
public class MedicalRecordServiceController {
	
	
	private static final Map<Integer, MedicalRecord> medicalrecordData = new HashMap<Integer, MedicalRecord>() {
		/**
		 * 
		 */

		{
			put(111,new MedicalRecord(311, 111,211));
			put(222,new MedicalRecord(312, 112,212));
		}
 
    };

    @ApiOperation(value = "Get list of medicalrecords in the System ", response = Iterable.class, tags = "getmedicalrecords")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @RequestMapping(value = "/getmedicalrecords")
    public Map<Integer, MedicalRecord> getmedicalrecords(){
        return medicalrecordData;
    }




    @ApiOperation(value = "Get medicalrecord with the specified id in the System ", response = MedicalRecord.class,
            tags =
            "getmedicalrecord")
    @RequestMapping(value = "/getmedicalrecord/{medicalrecordId}", method = RequestMethod.GET)
    public MedicalRecord getmedicalrecordDetails(@PathVariable int medicalrecordId) {
        System.out.println("Getting medicalrecord details for " + medicalrecordId);
 
        MedicalRecord medicalRecord = medicalrecordData.get(medicalrecordId);
        if (medicalRecord == null) {
            
        	medicalRecord = new MedicalRecord(0, 0,0);
            
        }
        return medicalRecord;
    }

    @ApiOperation(value = "Add a medicalrecord", response = MedicalRecord.class, tags =
            "addmedicalrecord")
    @PostMapping("/addmedicalrecord")
    public Map<Integer, MedicalRecord> createmedicalrecord(@RequestBody int id, String name) {
        medicalrecordData.put(id, new MedicalRecord(id, name));
        return medicalrecordData;
    }

    @ApiOperation(value = "Update a medicalrecord", response = MedicalRecord.class, tags =
            "updatemedicalrecord")
    @PutMapping("updatemedicalrecord/{id}")
    public MedicalRecord updatemedicalrecord(@PathVariable int id, @RequestBody String name) {
        medicalrecordData.get(id).setName(name);
        medicalrecordData.put(id, medicalrecordData.get(id));
        return medicalrecordData.get(id);
    }

    @ApiOperation(value = "Delete the specified medicalrecord", response = MedicalRecord.class, tags =
            "deletemedicalrecord")
    @DeleteMapping("deletemedicalrecord/{id}")
    public void deletePerson(@PathVariable int id) {
        medicalrecordData.remove(id);
    }




}
