package com.example.connectdb.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@Accessors(chain = true)
@Data
@Table(name = "order")
@Entity
public class Order {
    @Id
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "order_no")
    private String orderNo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column(name = "total_price")
    private BigDecimal totalPrice;


}
