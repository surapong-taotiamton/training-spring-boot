package com.example.testmapstruct.mapper;

import com.example.testmapstruct.entity.Shop;
import com.example.testmapstruct.service.spec.ShopServiceSpec;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ShopServiceImplMapper {

    ShopServiceSpec.ShopInfo toShopInfo(Shop shop);

}
