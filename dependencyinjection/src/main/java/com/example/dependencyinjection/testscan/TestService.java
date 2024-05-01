package com.example.dependencyinjection.testscan;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public TestService() {
        System.out.println("TEST SERVICE");
    }

    public String getData() {
        return "test service data";
    }
}
