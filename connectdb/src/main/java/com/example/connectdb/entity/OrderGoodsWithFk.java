package com.example.connectdb.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@Accessors(chain = true)
@Data
@Entity
@Table(name = "order_goods")
public class OrderGoodsWithFk {

    @EmbeddedId
    private OrderGoodsPk id;

    @MapsId("goodsId")
    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @MapsId("orderId")
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    @Column(name = "number_of_piece")
    private Integer numberOfPiece;

    @Column(name = "price_per_piece")
    private BigDecimal pricePerPiece;

    @Column(name = "sum")
    private BigDecimal sum;
}
