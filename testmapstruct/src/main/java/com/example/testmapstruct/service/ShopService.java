package com.example.testmapstruct.service;

import com.example.testmapstruct.service.spec.ShopServiceSpec;

public interface ShopService {

    ShopServiceSpec.ShopInfo getShop(String shopId);
    ShopServiceSpec.GoodsInfo getGoodsInfo(String goodsId);
    ShopServiceSpec.GoodsFullInfo getGoodsFullInfo(String goodsId);

}
