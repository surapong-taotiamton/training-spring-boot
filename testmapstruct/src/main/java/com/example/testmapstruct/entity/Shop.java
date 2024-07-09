package com.example.testmapstruct.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;


@Accessors(chain = true)
@Data
@Entity
public class Shop {

    @Id
    private String shopId;

    private String shopName;

    private String shopAddress;

    private String shopEmail;

    private String shopContactNo;

    private String ownerFirstname;

    private String ownerLastname;

}
