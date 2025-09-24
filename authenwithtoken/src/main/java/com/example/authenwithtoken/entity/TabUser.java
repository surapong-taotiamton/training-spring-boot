package com.example.authenwithtoken.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


@Accessors(chain = true)
@Data
@Entity
public class TabUser {
    @Id
    private String userId;
    private String username;
    private String password;
    private String token;
    private LocalDateTime lastLogin;
    private LocalDateTime lastConnectServer;
}
