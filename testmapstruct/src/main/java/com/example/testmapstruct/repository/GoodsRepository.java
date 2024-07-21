package com.example.testmapstruct.repository;

import com.example.testmapstruct.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, String> {
}
