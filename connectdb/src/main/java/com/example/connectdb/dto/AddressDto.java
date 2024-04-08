package com.example.connectdb.dto;


import com.example.connectdb.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class AddressDto {
    private String userId;
    private String username;
    private String houseNo;
    private String subDistrict;
    private String district;
    private String city;
    private String postcode;
}
