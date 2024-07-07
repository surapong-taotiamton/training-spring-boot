package com.example.testlombok;


import com.example.testlombok.controller.dto.AddressDto;

public class AddressWork {

    public static void main(String[] args) {

        //AddressDto addressDto = new AddressDto("HOUSE_ID", "HOUSE_NO");

        //AddressDto addressDto = new AddressDto("HOUSE_ID", "HOUSE_NO", "HOUSE_ROAD", "DATA", "SECRET_DATA");

        AddressDto addressDto = new AddressDto("HOUSE_ID", "HOUSE_NO", "HOUSE_ROAD");

        System.out.println(addressDto);

    }

}
