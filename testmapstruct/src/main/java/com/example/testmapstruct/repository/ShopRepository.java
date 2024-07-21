package com.example.testmapstruct.repository;

import com.example.testmapstruct.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, String> {
}
