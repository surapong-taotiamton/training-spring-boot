package com.example.connectdb.repository;


import com.example.connectdb.dto.GoodsDto;
import com.example.connectdb.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, String> {

    List<Goods> findByShopShopId(String shopId);

    @Query("SELECT g FROM Goods g WHERE g.shop.shopId = :shopId ")
    List<Goods> findByShopIdJpql(@Param("shopId") String shopId);


    @Query("SELECT new com.example.connectdb.dto.GoodsDto(g.goodsId, g.goodsName, g.goodsPrice, g.shop.shopId, g.shop.shopName) FROM Goods g WHERE g.shop.shopId = :shopId ")
    List<GoodsDto> findGoodsDtoByShopId(@Param("shopId") String shopId);

}
