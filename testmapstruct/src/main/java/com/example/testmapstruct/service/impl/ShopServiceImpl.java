package com.example.testmapstruct.service.impl;

import com.example.testmapstruct.entity.Shop;
import com.example.testmapstruct.mapper.ShopServiceImplMapper;
import com.example.testmapstruct.repository.ShopRepository;
import com.example.testmapstruct.service.ShopService;
import com.example.testmapstruct.service.spec.ShopServiceSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    private final ShopServiceImplMapper shopServiceImplMapper;

    @Override
    public ShopServiceSpec.ShopInfo getShop(String shopId) {
        Shop shop = shopRepository.findById(shopId).orElseThrow();
        return shopServiceImplMapper.toShopInfo(shop);
    }


    @Override
    public ShopServiceSpec.GoodsInfo getGoodsInfo(String goodsId) {
        return null;
    }

    @Override
    public ShopServiceSpec.GoodsFullInfo getGoodsFullInfo(String goodsId) {
        return null;
    }
}
