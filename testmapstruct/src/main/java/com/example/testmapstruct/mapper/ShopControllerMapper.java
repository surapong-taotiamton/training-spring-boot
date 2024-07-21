package com.example.testmapstruct.mapper;

import com.example.testmapstruct.controller.dto.ForThirdPartyDto;
import com.example.testmapstruct.service.spec.ShopServiceSpec;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ShopControllerMapper {

    @Mapping(target = "name", source = "shopName")
    @Mapping(target = "contactNo", source = "shopContactNo")
    @Mapping(target = "address", source = "shopAddress")
    @Mapping(target = "email", source = "shopEmail")
    ForThirdPartyDto.ShopInfo toShopInfo(ShopServiceSpec.ShopInfo serviceData);

}
