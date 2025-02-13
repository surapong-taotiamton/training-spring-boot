package com.example.authentication.controller;

import com.example.authentication.controller.dto.SecretData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretDataController {

    @PostMapping("/api/get-secret-data")
    public SecretData getSecretData( ) {
        return new SecretData()
                .setTitle("CUTE PAT")
                .setDescription("PAT IS VERY CUTE");
    }

    @PostMapping("/api/get-secret-data-2")
    public SecretData getSecretData2( ) {
        return new SecretData()
                .setTitle("CUTE BELLE")
                .setDescription("BELLE IS VERY CUTE");
    }




}
