package com.example.connectdb.repository;

import com.example.connectdb.entity.OrderGoodsPk;
import com.example.connectdb.entity.OrderGoodsWithFk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderGoodsWithFkRepository extends JpaRepository<OrderGoodsWithFk, OrderGoodsPk> {
}
