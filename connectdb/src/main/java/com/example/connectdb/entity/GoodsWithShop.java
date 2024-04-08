package com.example.connectdb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.math.BigDecimal;


@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table(name = "goods")
public class GoodsWithShop {

    @Id
    @Column(name = "goods_id")
    private String goodsId;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "goods_price")
    private BigDecimal goodsPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private ShopWithGoods shop;
}
