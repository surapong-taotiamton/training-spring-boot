package com.example.testmapstruct.controller;

import com.example.testmapstruct.controller.dto.ForThirdPartyDto;
import com.example.testmapstruct.entity.Shop;
import com.example.testmapstruct.mapper.ShopControllerMapper;
import com.example.testmapstruct.service.ShopService;
import com.example.testmapstruct.service.spec.ShopServiceSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("get-shop-for-third-party")
    public ForThirdPartyDto.ShopInfo getShopInfoForThirdParty(
            @RequestParam("shopId") String shopId
    ) {
        ShopServiceSpec.ShopInfo a = shopService.getShop(shopId);
        return shopControllerMapper.toShopInfo(a);
    }

}
