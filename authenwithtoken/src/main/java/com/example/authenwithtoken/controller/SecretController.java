package com.example.authenwithtoken.controller;

import com.example.authenwithtoken.controller.dto.SecretData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretController {
    @PostMapping("/api/get-secret")
    public SecretData get() {
        return new SecretData().setSecretValue("VALUE");
    }
}
