package com.example.authenwithtoken.service;

import com.example.authenwithtoken.entity.TabUser;
import com.example.authenwithtoken.repository.TabUserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class TabUserService {

    private final TabUserRepository tabUserRepository;

    @Cacheable(key = "#userId", value = "tabUserService")
    public TabUserServiceDto getById(String userId) {

        log.info("userId : {} : In method : getById  ", userId);
        return tabUserRepository.findById(userId).map(tabUser -> {
            return new TabUserServiceDto()
                    .setUserId(tabUser.getUserId())
                    .setUsername(tabUser.getUsername())
                    .setToken(tabUser.getToken())
                    .setLastLogin(tabUser.getLastLogin())
                    .setLastConnectServer(tabUser.getLastConnectServer());
        }).orElse(null);
    }


    @CachePut(key = "#request.userId", value = "tabUserService")
    public TabUserServiceDto updateTabUser(TabUserServiceDto request) {

        log.info("userId : {} : In method : updateTabUser  ", request.getUserId());

        TabUser tabUser = tabUserRepository.findById(request.userId).orElseThrow();

        tabUser.setUsername(request.username)
                .setToken(request.token)
                .setLastLogin(request.getLastLogin())
                .setLastConnectServer(request.getLastConnectServer());
        tabUser = tabUserRepository.save(tabUser);


        return new TabUserServiceDto()
                .setUserId(tabUser.getUserId())
                .setUsername(tabUser.getUsername())
                .setToken(tabUser.getToken())
                .setLastLogin(tabUser.getLastLogin())
                .setLastConnectServer(tabUser.getLastConnectServer());
    }


    @CacheEvict(key = "#userId", value = "tabUserService")
    public void deleteUser(String userId) {
        tabUserRepository.deleteById(userId);
    }









    @Accessors(chain = true)
    @Data
    public static class TabUserServiceDto {
        private String userId;
        private String username;
        private String token;
        private LocalDateTime lastLogin;
        private LocalDateTime lastConnectServer;
    }


    @Accessors(chain = true)
    @Data
    public static class ChangePasswordRequest {
        private String userId;
        private String password;
    }

}
