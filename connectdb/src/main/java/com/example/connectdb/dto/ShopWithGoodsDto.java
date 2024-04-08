package com.example.connectdb.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class ShopWithGoodsDto {
    private ShopDto shopDto;
    private List<GoodsDto> listOfGoods;
}
