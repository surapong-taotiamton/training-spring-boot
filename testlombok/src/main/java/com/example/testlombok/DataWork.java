package com.example.testlombok;

import com.example.testlombok.controller.dto.DataDto;

public class DataWork {


    public static void main(String[] args) {

        DataDto dataDto = new DataDto();
        dataDto.setData1("PAT");
        dataDto.setData2("BELLE");
        dataDto.setData3("PUYNUNN");

        System.out.println(dataDto);

    }
}
