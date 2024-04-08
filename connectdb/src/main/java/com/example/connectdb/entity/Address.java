package com.example.connectdb.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;


@Accessors(chain = true)
@Data
@Table(name = "address")
@Entity
public class Address {

    @Id
    @Column(name = "user_id")
    private String userId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "house_no")
    private String houseNo;

    @Column(name = "sub_district")
    private String subDistrict;

    @Column(name = "district")
    private String district;

    @Column(name = "city")
    private String city;

    @Column(name = "postcode")
    private String postcode;


}
