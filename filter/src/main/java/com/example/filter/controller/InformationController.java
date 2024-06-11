package com.example.filter.controller;

import com.example.filter.controller.dto.InformationControllerDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InformationController {

    @GetMapping("secret/get-information")
    public InformationControllerDto.InformationData getInformationData() {
        return new InformationControllerDto.InformationData()
                .setName("PAT")
                .setAge(26);
    }

}
