package com.example.connectdb.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Accessors(chain = true)
@Data
@Entity
@Table(name = "shop")
public class ShopWithGoods {

    @Id
    @Column(name = "shop_id")
    private String shopId;

    @Column(name = "shop_name")
    private String shopName;

    @OneToMany(
        cascade = CascadeType.ALL,
        mappedBy = "shop",
        orphanRemoval = true
    )
    private List<GoodsWithShop> goods = new ArrayList<>();


    public void addGoods(GoodsWithShop goodsWithShop) {
        goodsWithShop.setShop(this);
        goods.add(goodsWithShop);
    }

    public void removeGoods(GoodsWithShop goodsWithShop) {
        goods.remove(goodsWithShop);
        goodsWithShop.setShop(null);
    }

    public void removeAllGoods() {
        Iterator<GoodsWithShop> itr = this.goods.iterator();

        while (itr.hasNext()) {
            GoodsWithShop goodsWithShop = itr.next();
            goodsWithShop.setShop(null);
            itr.remove();
        }

    }

}
