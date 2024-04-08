package com.example.connectdb.dto;


import jakarta.persistence.Column;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Accessors(chain = true)
@Data
public class OrderGoodsDto {
    private String orderId;
    private String goodsId;
    private Integer numberOfPiece;
    private BigDecimal pricePerPiece;
    private BigDecimal sum;
}
