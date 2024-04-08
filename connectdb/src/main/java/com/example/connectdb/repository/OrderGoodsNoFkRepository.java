package com.example.connectdb.repository;

import com.example.connectdb.entity.OrderGoodsNoFk;
import com.example.connectdb.entity.OrderGoodsPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderGoodsNoFkRepository extends JpaRepository<OrderGoodsNoFk, OrderGoodsPk> {
}
