package com.example.connectdb;

import com.example.connectdb.dto.ShopDto;
import com.example.connectdb.entity.Shop;
import com.example.connectdb.repository.ShopRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TestJpaController {

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping("jpa/getById")
    public Shop getById(
            @RequestParam("id") String id
    ) {
        return shopRepository.findById(id).orElse(null);
    }

    @PostMapping("jpa/insert")
    public Shop insert(
            @RequestBody Shop shop
    ) {
        return shopRepository.save(shop);
    }


    @GetMapping("jpa/manual-insert")
    public Shop insertByManual() {
        Shop shop = new Shop();
        shop.setShopId(UUID.randomUUID().toString());
        shop.setShopName("AAAA");

        System.out.println(shop);
        return shopRepository.save(shop);
    }


    @PostMapping("jpa/update")
    public Shop update(
            @RequestBody Shop shop
    ) {
        Shop shopInDb = shopRepository.findById(shop.getShopId()).orElseThrow();
        return shopRepository.save(shop);

    }


    @GetMapping("jpa/delete")
    public void delete(
            @RequestParam("id") String id
    ) {
        shopRepository.findById(id).orElseThrow();
        shopRepository.deleteById(id);
    }


    @GetMapping("jpa/find-by-shop-name")
    public List<Shop> testFindByShopName(
            @RequestParam("shopName")  String shopName) {
        return shopRepository.findByShopName(shopName);
    }

    @GetMapping("jpa/find-by-shop-name-not-like")
    public List<Shop> testFindByShopNameNotLike(
            @RequestParam("shopName")  String shopName) {
        return shopRepository.findByShopNameNotLike(shopName);
    }

    @GetMapping("jpa/find-by-shop-name-jpql")
    public List<Shop> testFindByShopNameWithJpql(
            @RequestParam("shopName")  String shopName) {
        return shopRepository.findByJpqlShopName(shopName);
    }


}
