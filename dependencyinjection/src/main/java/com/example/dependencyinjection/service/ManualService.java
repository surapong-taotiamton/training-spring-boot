package com.example.dependencyinjection.service;


import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class ManualService {

    private String name;
    private String type;

    public ManualService(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void print() {
        System.out.println("Hello world : " + name + " : " + type);
    }
}
