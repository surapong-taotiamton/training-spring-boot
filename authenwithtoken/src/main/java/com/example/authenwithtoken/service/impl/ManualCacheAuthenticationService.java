package com.example.authenwithtoken.service.impl;

import com.example.authenwithtoken.entity.TabUser;
import com.example.authenwithtoken.repository.TabUserRepository;
import com.example.authenwithtoken.service.AuthenticationServiceInterface;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManualCacheAuthenticationService implements AuthenticationServiceInterface {

    private final CacheManager cacheManager;
    private final TabUserRepository tabUserRepository;
    private final String CACHE_NAME = "cacheToken";


    @Override
    public String genToken(String username, String password) {

        log.info("In method genToken : {}", username);

        TabUser tabUser = tabUserRepository.findByUsername(username).orElse(null);

        if (tabUser == null || !password.equals(tabUser.getPassword())) {
            return null;
        } else {
            LocalDateTime currentDateTime = LocalDateTime.now();
            String token = RandomStringUtils.secure().nextAlphanumeric(50);
            tabUser.setToken(token);
            tabUser.setLastLogin(currentDateTime);
            tabUser.setLastConnectServer(currentDateTime);
            tabUserRepository.save(tabUser);

            TabUserInformation tabUserInformation = new TabUserInformation()
                    .setUserId(tabUser.getUserId())
                    .setUsername(tabUser.getUsername())
                    .setToken(tabUser.getToken())
                    .setLastLogin(tabUser.getLastLogin())
                    .setLastConnectServer(tabUser.getLastConnectServer());

            cacheManager.getCache(CACHE_NAME).put(tabUserInformation.getUserId(), tabUserInformation);

            return String.format("%s:%s", tabUser.getUserId(), token);
        }
    }

    @Override
    public boolean verifyToken(String tokenFromRequest) {


        if (tokenFromRequest == null) {
            return false;
        }

        String[] splitToken = tokenFromRequest.split(":");
        String uid = splitToken[0];
        String tokenAfterSplit = splitToken[1];

        log.info("In verify token uid : {}", uid);

        TabUserInformation tabUserInformation = cacheManager.getCache(CACHE_NAME).get(uid, TabUserInformation.class);

        if (tabUserInformation == null) {
            log.info("Case not found in cache : uid : {}", uid);

            TabUser tabUser = tabUserRepository.findById(uid).orElse(null);

            if (tabUser == null) {
                return false;
            }

            tabUserInformation = new TabUserInformation()
                    .setUserId(tabUser.getUserId())
                    .setUsername(tabUser.getUsername())
                    .setToken(tabUser.getToken())
                    .setLastLogin(tabUser.getLastLogin())
                    .setLastConnectServer(tabUser.getLastConnectServer());
            cacheManager.getCache(CACHE_NAME).put(uid, tabUserInformation);

            log.info("Put data to cache complete : uid : {}", uid);
        } else {
            log.info("Case found data in cache : {} ", uid);
        }

        boolean correctToken = ( tokenAfterSplit != null && tokenAfterSplit.equals(tabUserInformation.getToken()) );

        LocalDateTime currentDateTime = LocalDateTime.now();

        log.info("Last login server : {}", tabUserInformation.getLastLogin());
        log.info("Last connect server : {}", tabUserInformation.getLastConnectServer());
        log.info("Current time : {}", currentDateTime);

        boolean exceedTokenLifetimeAfterLogin =  currentDateTime.isAfter ( tabUserInformation.getLastLogin().plus(8, ChronoUnit.HOURS) );
        boolean exceedLastConnectServer = currentDateTime.isAfter( tabUserInformation.getLastConnectServer().plus(5, ChronoUnit.MINUTES));

        log.info("correctToken : {}", correctToken);
        log.info("exceedTokenLifetimeAfterLogin : {}", exceedTokenLifetimeAfterLogin);
        log.info("exceedLastConnectServer : {}", exceedLastConnectServer);

        return correctToken && !exceedTokenLifetimeAfterLogin && !exceedLastConnectServer;
    }

    @Override
    public void updateLastConnectServer(String tokenFromRequest) {

        String[] splitToken = tokenFromRequest.split(":");
        String uid = splitToken[0];


        TabUserInformation tabUserInformation = cacheManager.getCache(CACHE_NAME).get(uid, TabUserInformation.class);

        long lifetimeCacheInSec = 600;


        if (tabUserInformation == null) {
            TabUser tabUser = tabUserRepository.findById(uid).orElseThrow();
            tabUserInformation = new TabUserInformation()
                    .setUserId(tabUser.getUserId())
                    .setUsername(tabUser.getUsername())
                    .setToken(tabUser.getToken())
                    .setLastLogin(tabUser.getLastLogin())
                    .setLastConnectServer(tabUser.getLastConnectServer());
            cacheManager.getCache(CACHE_NAME).put(tabUserInformation.getUserId(), tabUserInformation);
        }

        boolean expireCache = tabUserInformation.getLastConnectServer().plusSeconds(lifetimeCacheInSec).isAfter(LocalDateTime.now());

        if (expireCache) {
            // ทำการ Update lastConnectServer ไปที่ database และทำการ update cache

            TabUser tabUser = tabUserRepository.findById(uid).orElseThrow();
            tabUser.setLastConnectServer(LocalDateTime.now());
            tabUser = tabUserRepository.save(tabUser);

            tabUserInformation = new TabUserInformation()
                    .setUserId(tabUser.getUserId())
                    .setUsername(tabUser.getUsername())
                    .setToken(tabUser.getToken())
                    .setLastLogin(tabUser.getLastLogin())
                    .setLastConnectServer(tabUser.getLastConnectServer());
            cacheManager.getCache(CACHE_NAME).put(tabUserInformation.getUserId(), tabUserInformation);

        } else {
            // ไม่ต้องทำอะไร เพราะ token ยังไม่ expire และเราไม่อยากกวน Database บ่อยๆ
        }

    }



    @Accessors(chain = true)
    @Data
    public static class TabUserInformation {
        private String userId;
        private String username;
        private String token;
        private LocalDateTime lastLogin;
        private LocalDateTime lastConnectServer;
    }

}
