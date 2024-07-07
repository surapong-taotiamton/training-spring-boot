package com.example.testlombok.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class AddressDto {
    private final String houseId;
    private final String houseNo;
    private String houseRoad;
    private String data;
    private String secretData;

    public AddressDto(String houseId, String houseNo, String houseRoad) {
        this.houseId = houseId;
        this.houseNo = houseNo;
        this.houseRoad = houseRoad;
    }

    @Override
    public String toString() {
        return "AddressDto{" +
                "houseId='" + houseId + '\'' +
                ", houseNo='" + houseNo + '\'' +
                ", houseRoad='" + houseRoad + '\'' +
                ", data='" + data + '\'' +
                ", secretData='" + secretData + '\'' +
                '}';
    }
}
