package com.example.testmapstruct.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@Accessors(chain = true)
@Data
@Table
@Entity
public class Goods {
    @Id
    private String goodsId;
    private String goodsName;

    @ManyToOne
    @JoinColumn(name = "shopId")
    private Shop shop;

    private BigDecimal price;

}
