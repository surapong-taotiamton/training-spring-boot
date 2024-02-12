package com.example.mappingrequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormSubmitDataController {

    @PostMapping("login")
    public String login(
            @ModelAttribute UserCredential inputFromFormSubmit
    ) {
//        System.out.println("username : " + inputFromFormSubmit.getUsername());
//        System.out.println("password : " + inputFromFormSubmit.getPassword());

        return String.format("%s : %s", inputFromFormSubmit.getUsername(),inputFromFormSubmit.getPassword());
    }




}
