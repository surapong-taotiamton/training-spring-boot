package com.example.connectdb;

import com.example.connectdb.dto.*;
import com.example.connectdb.entity.Goods;
import com.example.connectdb.entity.GoodsWithShop;
import com.example.connectdb.entity.Shop;
import com.example.connectdb.entity.ShopWithGoods;
import com.example.connectdb.repository.ShopWithGoodsRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ShopWIthGoodsController {

    @Autowired
    private ShopWithGoodsRepository shopWithGoodsRepository;

    @GetMapping("/shopWithGoods/{shopId}")
    public ShopWithGoodsDto getShopWithGoods(
            @PathVariable("shopId") String shopId
    ) {
        ShopWithGoods shopWithGoods = shopWithGoodsRepository.findById(shopId).orElseThrow();
        return new ShopWithGoodsDto()
                .setShopDto(new ShopDto()
                        .setShopId(shopWithGoods.getShopId())
                        .setShopName(shopWithGoods.getShopName())
                ).setListOfGoods(
                        shopWithGoods.getGoods().stream().map(goods -> {
                            return new GoodsDto()
                                    .setGoodsId(goods.getGoodsId())
                                    .setGoodsName(goods.getGoodsName())
                                    .setGoodsPrice(goods.getGoodsPrice())
                                    .setShopId(goods.getShop().getShopId())
                                    .setShopName(goods.getShop().getShopName());
                        }).collect(Collectors.toList())
                );
    }


    @PostMapping("/shopWithGoods/insert")
    public ShopWithGoodsDto insertShopWithGoods(
            @RequestBody InsertShopWithGoodsDto insertShopWithGoodsDto
            ) {

        ShopWithGoods shopWithGoods = new ShopWithGoods()
                .setShopId(insertShopWithGoodsDto.getShopDto().getShopId())
                .setShopName(insertShopWithGoodsDto.getShopDto().getShopName());

        for (GoodsInShopWithGoodsDto goodsInShopWithGoodsDto : insertShopWithGoodsDto.getListOfGoods()) {
            GoodsWithShop goodsWithShop = new GoodsWithShop()
                    .setGoodsId(goodsInShopWithGoodsDto.getGoodsId())
                    .setGoodsPrice(goodsInShopWithGoodsDto.getGoodsPrice())
                    .setGoodsName(goodsInShopWithGoodsDto.getGoodsName());
            shopWithGoods.addGoods(goodsWithShop);
        }

        shopWithGoods = shopWithGoodsRepository.save(shopWithGoods);
        return new ShopWithGoodsDto()
                .setShopDto(new ShopDto()
                        .setShopId(shopWithGoods.getShopId())
                        .setShopName(shopWithGoods.getShopName())
                ).setListOfGoods(
                        shopWithGoods.getGoods().stream().map(goods -> {
                            return new GoodsDto()
                                    .setGoodsId(goods.getGoodsId())
                                    .setGoodsName(goods.getGoodsName())
                                    .setGoodsPrice(goods.getGoodsPrice())
                                    .setShopId(goods.getShop().getShopId())
                                    .setShopName(goods.getShop().getShopName());
                        }).collect(Collectors.toList())
                );
    }


    @GetMapping("/shopWithGoods/{shopId}/deleteAllGoods")
    public ShopWithGoodsDto deleteAllGoods(
            @PathVariable("shopId") String shopId
    ) {
        ShopWithGoods shopWithGoods = shopWithGoodsRepository.findById(shopId).orElseThrow();

        shopWithGoods.removeAllGoods();
        shopWithGoods = shopWithGoodsRepository.save(shopWithGoods);

        return new ShopWithGoodsDto()
                .setShopDto(new ShopDto()
                        .setShopId(shopWithGoods.getShopId())
                        .setShopName(shopWithGoods.getShopName())
                ).setListOfGoods(
                        shopWithGoods.getGoods().stream().map(goods -> {
                            return new GoodsDto()
                                    .setGoodsId(goods.getGoodsId())
                                    .setGoodsName(goods.getGoodsName())
                                    .setGoodsPrice(goods.getGoodsPrice())
                                    .setShopId(goods.getShop().getShopId())
                                    .setShopName(goods.getShop().getShopName());
                        }).collect(Collectors.toList())
                );
    }

}
