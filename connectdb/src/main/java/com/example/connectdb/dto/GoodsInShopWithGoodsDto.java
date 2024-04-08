package com.example.connectdb.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Accessors(chain = true)
@Data
public class GoodsInShopWithGoodsDto {
    private String goodsId;
    private String goodsName;
    private BigDecimal goodsPrice;
}
