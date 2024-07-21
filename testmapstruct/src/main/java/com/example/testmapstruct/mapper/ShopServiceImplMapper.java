package com.example.testmapstruct.mapper;

import com.example.testmapstruct.entity.Goods;
import com.example.testmapstruct.entity.Shop;
import com.example.testmapstruct.service.spec.ShopServiceSpec;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ShopServiceImplMapper {

    ShopServiceSpec.ShopInfo toShopInfo(Shop shop);

    @Mapping(target = "goodsId", source = "goods.goodsId")
    @Mapping(target = "goodsName", source = "goods.goodsName")
    @Mapping(target = "price", source = "goods.price")
    @Mapping(target = "shopId", source = "shop.shopId")
    @Mapping(target = "shopName", source = "shop.shopName")
    @Mapping(target = "shopContactNo", source = "shop.shopContactNo")
    ShopServiceSpec.GoodsFullInfo toGoodsFullInfo(Shop shop, Goods goods);

    @Mapping(target = "goodsId", source = "goods.goodsId")
    @Mapping(target = "goodsName", source = "goods.goodsName")
    @Mapping(target = "price", source = "goods.price")
    @Mapping(target = "shopId", source = "goods.shop.shopId")
    @Mapping(target = "shopName", source = "goods.shop.shopName")
    @Mapping(target = "shopContactNo", source = "goods.shop.shopContactNo")
    ShopServiceSpec.GoodsFullInfo toGoodsFullInfo(Goods goods);

    @Mapping(target = "shopId", source = "request.shopId")
    @Mapping(target = "shopName", source = "request.shopName")
    @Mapping(target = "shopAddress", source = "request.shopAddress")
    @Mapping(target = "shopEmail", source = "request.shopEmail")
    @Mapping(target = "shopContactNo", source = "request.shopContactNo")
    @Mapping(target = "ownerFirstname", ignore = true)
    @Mapping(target = "ownerLastname", ignore = true)
    Shop updateShop(@MappingTarget Shop shop, ShopServiceSpec.UpdateShopRequest request);


}
