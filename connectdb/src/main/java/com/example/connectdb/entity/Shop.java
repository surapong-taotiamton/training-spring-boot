package com.example.connectdb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;




@Accessors(chain = true)
@Data
@Entity
@Table(name = "shop")
public class Shop {

    @Id
    @Column(name = "shop_id")
    private String shopId;

    @Column(name = "shop_name")
    private String shopName;

}
