package com.example.dependencyinjection.testscan;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TestComponent {

    public TestComponent() {
        System.out.println("TEST COMPONENT");
    }

    public String getData() {
        return "test component data";
    }

}
