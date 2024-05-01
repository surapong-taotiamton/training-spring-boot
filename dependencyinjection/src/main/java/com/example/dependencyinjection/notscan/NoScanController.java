package com.example.dependencyinjection.notscan;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoScanController {

    public NoScanController() {
        System.out.println("THIS IS NO SCAN CONTROLLER");
    }
}
