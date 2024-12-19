package com.howtodoinjava.example.apigateway.controller;


import com.howtodoinjava.example.apigateway.delegate.PractitionerServiceDelegate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api(value = "PractitionerServiceController", description = "REST Apis related to Practitioner Entity")
public class PractitionerServiceController {

    @Autowired
    PractitionerServiceDelegate practitionerServiceDelegate;

    @ApiOperation(value = "Get list of practitioners in the System ", response = Iterable.class, tags = "getpractitioners")
    @GetMapping("/getPractitioners")
    public Map<String, String> getPractitioners() {
        return practitionerServiceDelegate.receiveAllPractitioners();
    }

    @ApiOperation(value = "Get practitioner with the specified name in the System ", response = Iterable.class, tags =
            "getpractitioner")
    @GetMapping("/getPractitionerbyName/{name}")
    public Map<String, String> getPractitionerbyName(@PathVariable String name) {
        return practitionerServiceDelegate.receivePractitionerByName(name);
    }

    @ApiOperation(value = "Get practitioner with the specified id in the System ", response = Iterable.class, tags =
            "getpractitioner")
    @GetMapping("/getPractitioner/{id}")
    public Map<String, String> getPractitioner(@PathVariable int id) {
        return practitionerServiceDelegate.receivePractitioner(id);
    }

    @ApiOperation(value = "Add a practitioner", response = Iterable.class, tags =
            "addPractitioner")
    @PostMapping("/addPractitioner")
    public Map<String, String> addPractitioner(@RequestBody int id, String name) {
        return practitionerServiceDelegate.addPractitioner(id, name);
    }

    @ApiOperation(value = "Update a practitioner", response = Iterable.class, tags =
            "updatePractitioner")
    @PutMapping("/updatePractitioner/{id}")
    public Map<String, String> updatePractitioner(@PathVariable int id, @RequestBody String name) {
        return practitionerServiceDelegate.updatePractitioner(id, name);
    }

    @ApiOperation(value = "Delete the specified practitioner", response = Iterable.class, tags =
            "deletepractitioner")
    @DeleteMapping("/deletePractitioner/{id}")
    public Map<String, String> deletePractitioner(@PathVariable int id) {
        return practitionerServiceDelegate.deletePractitioner(id);
    }
}
