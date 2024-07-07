package com.example.testlombok;

import com.example.testlombok.controller.dto.DataDto;

public class DataEqualWork {

    public static void main(String[] args) {


        DataDto dataDto1 = new DataDto();
        dataDto1.setData1("PAT");
        dataDto1.setData2("BELLE");
        dataDto1.setData3("PUYNUNN");


        DataDto dataDto2 = new DataDto();
        dataDto2.setData1("PAT");
        dataDto2.setData2("BELLE");
        dataDto2.setData3("PUYNUNN");


        System.out.printf("%s : %s \n", dataDto1, dataDto1.hashCode());
        System.out.printf("%s : %s \n", dataDto2, dataDto2.hashCode());
        System.out.println(dataDto1.equals(dataDto2));


    }

}
