package com.example.mappingrequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {

    @PostMapping("login-with-json")
    public String loginWithJson(
            @RequestBody UserCredential inputJsonFromClient
    ) {
        return String.format("%s : %s", inputJsonFromClient.getUsername(),inputJsonFromClient.getPassword());
    }

}
