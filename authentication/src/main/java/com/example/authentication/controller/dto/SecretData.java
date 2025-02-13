package com.example.authentication.controller.dto;


import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class SecretData {

    private String title;
    private String description;

}
