package com.example.testmapstruct.service.impl;

import com.example.testmapstruct.entity.Goods;
import com.example.testmapstruct.entity.Shop;
import com.example.testmapstruct.mapper.ShopServiceImplMapper;
import com.example.testmapstruct.repository.GoodsRepository;
import com.example.testmapstruct.repository.ShopRepository;
import com.example.testmapstruct.service.ShopService;
import com.example.testmapstruct.service.spec.ShopServiceSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    private final GoodsRepository goodsRepository;

    private final ShopServiceImplMapper shopServiceImplMapper;

    @Override
    public ShopServiceSpec.ShopInfo getShop(String shopId) {
        Shop shop = shopRepository.findById(shopId).orElseThrow();
        return shopServiceImplMapper.toShopInfo(shop);
    }

    @Override
    public ShopServiceSpec.ShopInfo updateShop(ShopServiceSpec.UpdateShopRequest updateShopRequest) {

        Shop shop = shopRepository.findById(updateShopRequest.getShopId()).orElseThrow();

        shop = shopServiceImplMapper.updateShop(shop, updateShopRequest);

        shop = shopRepository.save(shop);

        return shopServiceImplMapper.toShopInfo(shop);
    }

    @Override
    public ShopServiceSpec.GoodsInfo getGoodsInfo(String goodsId) {
        return null;
    }

    @Override
    public ShopServiceSpec.GoodsFullInfo getGoodsFullInfo(String goodsId) {

        Goods goods = goodsRepository.findById(goodsId).orElseThrow();
        Shop shop = goods.getShop();
        return shopServiceImplMapper.toGoodsFullInfo(goods);
    }
}
