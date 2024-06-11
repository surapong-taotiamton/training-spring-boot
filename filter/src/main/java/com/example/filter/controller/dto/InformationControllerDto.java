package com.example.filter.controller.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor(access = AccessLevel.NONE)
public class InformationControllerDto {

    @Accessors(chain = true)
    @Data
    public static class InformationData {
        private String name;
        private int age;
    }

}
