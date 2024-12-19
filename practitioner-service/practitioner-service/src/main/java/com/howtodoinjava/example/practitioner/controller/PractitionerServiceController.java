package com.howtodoinjava.example.practitioner.controller;

import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import com.howtodoinjava.example.practitioner.beans.Practitioner;

@RestController
//@RequestMapping(value = "practitioner/")
@Api(value = "Swagger2DemoRestController", description = "REST Apis related to Student Entity!!!!")
public class PractitionerServiceController {
	
	
	private static final Map<Integer, Practitioner> practitionerData = new HashMap<Integer, Practitioner>() {
		/**
		 * 
		 */

		{
			put(211,new Practitioner(211, "practitioner1"));
			put(222,new Practitioner(222, "practitioner2"));
		}
 
    };

    @ApiOperation(value = "Get list of practitioners in the System ", response = Iterable.class, tags = "getpractitioners")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @RequestMapping(value = "/getPractitioners")
    public Map<Integer, Practitioner> getpractitioners(){
        return practitionerData;
    }


    @ApiOperation(value = "Get practitioner with the specified name in the System ", response = Practitioner.class, tags =
            "getpractitioner")
    @RequestMapping(value = "/getpractitionerbyName/{name}")
    public Practitioner getpractitioner(@PathVariable(value = "name") String name) {
        for (Practitioner practitioner : practitionerData.values()) {
            if(practitioner.getName().equals((name))){
                return practitioner;
            }
        }
        return null;
    }

    @ApiOperation(value = "Get practitioner with the specified id in the System ", response = Practitioner.class, tags =
            "getpractitioner")
    @RequestMapping(value = "/getPractitioner/{practitionerId}", method = RequestMethod.GET)
    public Practitioner getpractitionerDetails(@PathVariable int practitionerId) {
        System.out.println("Getting practitioner details for " + practitionerId);
 
        Practitioner practitioner = practitionerData.get(practitionerId);
        if (practitioner == null) {
            
        	practitioner = new Practitioner(0, "N/A");
            
        }
        return practitioner;
    }

    @ApiOperation(value = "Add a practitioner", response = Practitioner.class, tags =
            "addPractitioner")
    @PostMapping("/addPractitioner")
    public Map<Integer, Practitioner> createpractitioner(@RequestBody int id, String name) {
        practitionerData.put(id, new Practitioner(id, name));
        return practitionerData;
    }

    @ApiOperation(value = "Update a practitioner", response = Practitioner.class, tags =
            "updatePractitioner")
    @PutMapping("updatePractitioner/{id}")
    public Practitioner updatepractitioner(@PathVariable int id, @RequestBody String name) {
        practitionerData.get(id).setName(name);
        practitionerData.put(id, practitionerData.get(id));
        return practitionerData.get(id);
    }

    @ApiOperation(value = "Delete the specified practitioner", response = Practitioner.class, tags =
            "deletepractitioner")
    @DeleteMapping("deletePractitioner/{id}")
    public void deletePerson(@PathVariable int id) {
        practitionerData.remove(id);
    }




}
