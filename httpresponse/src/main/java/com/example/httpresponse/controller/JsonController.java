package com.example.httpresponse.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {

    @GetMapping("test-json")
    public UserData simpleReturn() {

        UserData userData = new UserData();
        userData.setFirstname("firstname-simple");
        userData.setLastname("lastname-simple");
        return userData;
    }

    @GetMapping("test-401")
    public ResponseEntity<Object>  returnStatus401(
            @RequestParam("input") String input
    ) {

        if("hacker".equals(input)) {

            ErrorObject errorObject = new ErrorObject();
            errorObject.setErrorMessage("HACKER TRY TO HACK");

            return ResponseEntity.status(401).body(errorObject);
        } else {
            UserData userData = new UserData();
            userData.setFirstname("firstname-simple");
            userData.setLastname("lastname-simple");
            return ResponseEntity.ok(userData);
        }
    }


}
