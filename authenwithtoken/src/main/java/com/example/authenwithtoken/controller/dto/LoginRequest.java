package com.example.authenwithtoken.controller.dto;


import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class LoginRequest {

    private String username;
    private String password;
}
