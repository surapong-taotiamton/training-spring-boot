package com.example.dependencyinjection;

import com.example.dependencyinjection.controller.TestInjectionController;
import com.example.dependencyinjection.testscan.TestComponent;
import com.example.dependencyinjection.testscan.TestService;

public class TestOtherFramework {

    public static void main(String[] args) {

        TestComponent testComponent = new TestComponent();
        TestService testService = new TestService();
        TestInjectionController testInjectionController = new TestInjectionController();

    }

}
