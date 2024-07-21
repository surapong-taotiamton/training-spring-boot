package com.example.testmapstruct.controller;

import com.example.testmapstruct.controller.dto.ForThirdPartyDto;
import com.example.testmapstruct.entity.Shop;
import com.example.testmapstruct.mapper.ShopControllerMapper;
import com.example.testmapstruct.service.ShopService;
import com.example.testmapstruct.service.spec.ShopServiceSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ShopController {

    private final ShopService shopService;

    private final ShopControllerMapper shopControllerMapper;

    @GetMapping("/get-shop")
    public ShopServiceSpec.ShopInfo getShopInfo(
            @RequestParam("shopId") String shopId
    ) {
        return shopService.getShop(shopId);
    }

    @PostMapping("/update-shop")
    public ShopServiceSpec.ShopInfo updateShopInfo(
            @RequestBody ShopServiceSpec.UpdateShopRequest request
    ) {
        return shopService.updateShop(request);
    }



    @GetMapping("/get-goods-full-info")
    public ShopServiceSpec.GoodsFullInfo getGoodsFullInfo(
            @RequestParam("goodsId") String goodsId
    ) {
        return shopService.getGoodsFullInfo(goodsId);
    }


    @GetMapping("get-shop-for-third-party")
    public ForThirdPartyDto.ShopInfo getShopInfoForThirdParty(
            @RequestParam("shopId") String shopId
    ) {
        ShopServiceSpec.ShopInfo a = shopService.getShop(shopId);
        return shopControllerMapper.toShopInfo(a);
    }

    @GetMapping("get-shop-for-external")
    public ForThirdPartyDto.ShopInfoExternalData getShopInfoExternalData(
            @RequestParam("shopId") String shopId
    ) {
        return shopControllerMapper.toShopInfoExternalData( shopService.getShop(shopId) );
    }

}
