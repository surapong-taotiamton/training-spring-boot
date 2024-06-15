package com.example.filter.controller;

import com.example.filter.controller.dto.InformationControllerDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopSecretInformationController {

    @GetMapping("top-secret/get-information-1")
    public InformationControllerDto.InformationData getTopSecretInfo1() {
        return new InformationControllerDto.InformationData()
                .setName("name")
                .setValue("WASINEE");
    }

    @GetMapping("top-secret/get-information-2")
    public InformationControllerDto.InformationData getTopSecretInfo2() {
        return new InformationControllerDto.InformationData()
                .setName("name")
                .setValue("PICHAYA");
    }


}
