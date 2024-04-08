package com.example.connectdb.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class InsertShopWithGoodsDto {
    private ShopDto shopDto;
    private List<GoodsInShopWithGoodsDto> listOfGoods;
}
