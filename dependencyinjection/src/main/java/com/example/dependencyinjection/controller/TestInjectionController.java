package com.example.dependencyinjection.controller;

import com.example.dependencyinjection.service.ManualService;
import com.example.dependencyinjection.testscan.TestComponent;
import com.example.dependencyinjection.testscan.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestInjectionController {

//    public TestInjectionController(
//            @Autowired TestComponent testComponent,
//            @Autowired TestService testService,
//            @Autowired ManualService manualService) {
//        this.testComponent = testComponent;
//        this.testService = testService;
//        this.manualService = manualService;
//    }

    @Autowired
    private TestComponent testComponent;

    @Autowired
    private TestService testService;

    @Autowired
    @Qualifier("POP")
    private ManualService manualService;


    @GetMapping("/test-component")
    public String getTestComponentData() {
        return testComponent.getData();
    }

    @GetMapping("/test-service")
    public String getTestServiceData() {
        return testService.getData();
    }

    @GetMapping("/test-manual")
    public String getManual() {
        return manualService.getName();
    }


}
