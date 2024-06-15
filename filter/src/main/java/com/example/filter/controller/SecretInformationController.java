package com.example.filter.controller;

import com.example.filter.controller.dto.InformationControllerDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretInformationController {

    @GetMapping("secret/get-information-1")
    public InformationControllerDto.InformationData getInformationData1() {

        return new InformationControllerDto.InformationData()
                .setName("name")
                .setValue("PAT");
    }

    @GetMapping("secret/get-information-2")
    public InformationControllerDto.InformationData getInformationData2() {
        return new InformationControllerDto.InformationData()
                .setName("name")
                .setValue("BELLE");
    }

    @GetMapping("secret/get-information-3")
    public InformationControllerDto.InformationData getInformationData3() {
        return new InformationControllerDto.InformationData()
                .setName("name")
                .setValue("PUYNUNN");
    }



}
