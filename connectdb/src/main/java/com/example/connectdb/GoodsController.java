package com.example.connectdb;

import com.example.connectdb.dto.GoodsDto;
import com.example.connectdb.dto.InsertGoodsDto;
import com.example.connectdb.entity.Goods;
import com.example.connectdb.entity.Shop;
import com.example.connectdb.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GoodsController {

    @Autowired
    private GoodsRepository goodsRepository;

    @GetMapping("/goods/{id}")
    public GoodsDto getGoods(
            @PathVariable("id") String id
    ) {
        Goods goods = goodsRepository.findById(id).orElseThrow();
        return new GoodsDto()
                .setGoodsId(goods.getGoodsId())
                .setGoodsName(goods.getGoodsName())
                .setGoodsPrice(goods.getGoodsPrice())
                .setShopId(goods.getShop().getShopId())
                .setShopName(goods.getShop().getShopName());
    }

    @PostMapping("/goods/insert")
    public GoodsDto insertGoods(
            @RequestBody InsertGoodsDto insertGoodsDto
            ) {

        Goods goods = new Goods()
                .setGoodsId(insertGoodsDto.getGoodsId())
                .setGoodsPrice(insertGoodsDto.getGoodsPrice())
                .setGoodsName(insertGoodsDto.getGoodsName())
                .setShop(new Shop().setShopId(insertGoodsDto.getShopId()));

        goods = goodsRepository.save(goods);
        return new GoodsDto()
                .setGoodsId(goods.getGoodsId())
                .setGoodsName(goods.getGoodsName())
                .setGoodsPrice(goods.getGoodsPrice())
                .setShopId(goods.getShop().getShopId())
                .setShopName(goods.getShop().getShopName());
    }


    @GetMapping("/goods")
    public List<GoodsDto> searchByShopId(
            @RequestParam("shopId") String shopId
    ) {
        return goodsRepository.findByShopShopId(shopId).stream().map(goods -> {
            return new GoodsDto()
                    .setGoodsId(goods.getGoodsId())
                    .setGoodsName(goods.getGoodsName())
                    .setGoodsPrice(goods.getGoodsPrice())
                    .setShopId(goods.getShop().getShopId())
                    .setShopName(goods.getShop().getShopName());
        }).collect(Collectors.toList());
    }

    @GetMapping("/goods/jpql")
    public List<GoodsDto> searchByShopIdJpql(
            @RequestParam("shopId") String shopId
    ) {
        return goodsRepository.findByShopIdJpql(shopId).stream().map(goods -> {
            return new GoodsDto()
                    .setGoodsId(goods.getGoodsId())
                    .setGoodsName(goods.getGoodsName())
                    .setGoodsPrice(goods.getGoodsPrice())
                    .setShopId(goods.getShop().getShopId())
                    .setShopName(goods.getShop().getShopName());
        }).collect(Collectors.toList());
    }

    @GetMapping("/goods/goodsDto")
    public List<GoodsDto> searchGoodsDto(
            @RequestParam("shopId") String shopId
    ) {
        return goodsRepository.findGoodsDtoByShopId(shopId);
    }







}
