package com.example.connectdb.repository;

import com.example.connectdb.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, String> {
    List<Shop> findByShopName(String shopName);
    List<Shop> findByShopNameNotLike(String shopName);

    @Query(
            "SELECT t FROM Shop AS t WHERE t.shopName = :inputString"
    )
    List<Shop> findByJpqlShopName(@Param("inputString") String inputShopName);

}
