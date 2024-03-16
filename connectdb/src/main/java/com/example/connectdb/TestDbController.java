package com.example.connectdb;

import com.example.connectdb.dto.ShopDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestDbController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/test-db")
    public void testDb() {

        List<Tuple> resultFromDb = entityManager.createNativeQuery("SELECT * FROM shop", Tuple.class).getResultList();
        for (Tuple tuple : resultFromDb) {
            String shopId = tuple.get("shop_id", String.class);
            String shopName = tuple.get("shop_name", String.class);
            System.out.println(shopId + ": " + shopName);
        }
    }


    @GetMapping("/getById")
    public ShopDto getById(
        @RequestParam("id") String id
    ) {

        List<Tuple> resultFromDb = entityManager.createNativeQuery("SELECT * FROM shop WHERE shop_id = :inputId ", Tuple.class)
                .setParameter("inputId", id)
                .getResultList();

        if (resultFromDb.isEmpty()) {
            return null;
        } else {
            Tuple tuple = resultFromDb.get(0);
            ShopDto shopDto = new ShopDto();
            shopDto.setShopId(tuple.get("shop_id", String.class));
            shopDto.setShopName(tuple.get("shop_name", String.class));
            return shopDto;
        }
    }


    @Transactional
    @PostMapping("/insert")
    public boolean insert(
            @RequestBody ShopDto shopDto
    ) {
        int rowEffect = entityManager.createNativeQuery("INSERT INTO shop ('shop_id', 'shop_name') VALUES( :shopId, :shopName ) ")
                .setParameter("shopId", shopDto.getShopId())
                .setParameter("shopName", shopDto.getShopName())
                .executeUpdate();
        return rowEffect > 0;
    }


    @Transactional
    @PostMapping("/update")
    public boolean update(
            @RequestBody ShopDto shopDto
    ) {
        int rowEffect = entityManager.createNativeQuery("UPDATE shop SET shop_id = :shopId , shop_name = :shopName WHERE shop_id = :shopId")
                .setParameter("shopId", shopDto.getShopId())
                .setParameter("shopName", shopDto.getShopName())
                .executeUpdate();
        return rowEffect > 0;
    }


    @Transactional
    @GetMapping("/delete")
    public boolean delete(
           @RequestParam("id") String id
    ) {
        int rowEffect = entityManager.createNativeQuery("DELETE FROM shop WHERE shop_id = :shopId")
                .setParameter("shopId", id)
                .executeUpdate();
        return rowEffect > 0;
    }






}
