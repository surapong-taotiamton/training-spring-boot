package com.example.dependencyinjection.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    public TestController() {
        System.out.println("THIS IS IN Contructor of TestController");
    }

    @GetMapping("helloworld")
    public String helloWorld() {
        return "HELLO WORLD";
    }

}
