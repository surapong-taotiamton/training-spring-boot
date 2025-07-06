package com.example.authenwithtoken.repository;

import com.example.authenwithtoken.entity.TabUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TabUserRepository extends JpaRepository<TabUser, String> {
    Optional<TabUser> findByUsername(String username);
}
