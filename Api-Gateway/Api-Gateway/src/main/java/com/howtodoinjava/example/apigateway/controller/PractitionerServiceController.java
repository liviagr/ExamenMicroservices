package com.howtodoinjava.example.apigateway.controller;


import com.howtodoinjava.example.apigateway.delegate.PractitionerServiceDelegate;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api(value = "Swagger2DemoRestController", description = "REST Apis related to Student Entity!!!!")
public class PractitionerServiceController {

    @Autowired
    PractitionerServiceDelegate practitionerServiceDelegate;

    // TODO REST : expose school + call student service to det data
    @GetMapping("/getPractitioners")
    public Map<String, String> getPractitioners() {
        return practitionerServiceDelegate.receiveAllPractitioners();
    }

    @GetMapping("/getPractitionerbyName/{name}")
    public Map<String, String> getPractitionerbyName(@PathVariable String name) {
        return practitionerServiceDelegate.receivePractitionerByName(name);
    }

    @GetMapping("/getPractitioner/{id}")
    public Map<String, String> getPractitioner(@PathVariable int id) {
        return practitionerServiceDelegate.receivePractitioner(id);
    }

    @PostMapping("/addPractitioner")
    public Map<String, String> addPractitioner(@RequestBody int id, String name) {
        return practitionerServiceDelegate.addPractitioner(id, name);
    }

    @PutMapping("/updatePractitioner/{id}")
    public Map<String, String> updatePractitioner(@PathVariable int id, @RequestBody String name) {
        return practitionerServiceDelegate.updatePractitioner(id, name);
    }

    @DeleteMapping("/deletePractitioner/{id}")
    public Map<String, String> deletePractitioner(@PathVariable int id) {
        return practitionerServiceDelegate.deletePractitioner(id);
    }
}
