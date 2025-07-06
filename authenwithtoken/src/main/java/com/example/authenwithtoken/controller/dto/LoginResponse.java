package com.example.authenwithtoken.controller.dto;


import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class LoginResponse {
    private String token;
}
