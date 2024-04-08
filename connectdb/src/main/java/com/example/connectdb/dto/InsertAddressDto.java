package com.example.connectdb.dto;


import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class InsertAddressDto {
    private String userId;
    private String houseNo;
    private String subDistrict;
    private String district;
    private String city;
    private String postcode;
}
