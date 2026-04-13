package com.example.authenwithtoken.service.impl;

import com.example.authenwithtoken.entity.TabUser;
import com.example.authenwithtoken.repository.TabUserRepository;
import com.example.authenwithtoken.service.AuthenticationServiceInterface;
import com.example.authenwithtoken.service.TabUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@RequiredArgsConstructor
public class AnnotationCacheAuthenticationService  implements AuthenticationServiceInterface {

    private final TabUserRepository tabUserRepository;
    private final TabUserService tabUserService;

    @Override
    public String genToken(String username, String password) {

        TabUser tabUser = tabUserRepository.findByUsername(username).orElse(null);

        if (tabUser == null || !password.equals(tabUser.getPassword())) {
            return null;
        } else {
            LocalDateTime currentDateTime = LocalDateTime.now();
            String token = RandomStringUtils.secure().nextAlphanumeric(50);
            tabUserService.updateTabUser(new TabUserService.TabUserServiceDto()
                    .setUserId(tabUser.getUserId())
                    .setUsername(tabUser.getUsername())
                    .setToken(token)
                    .setLastLogin(currentDateTime)
                    .setLastConnectServer(currentDateTime)
            );
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
        TabUserService.TabUserServiceDto tabUserServiceDto = tabUserService.getById(uid);


        boolean correctToken =  ( tokenAfterSplit != null && tokenAfterSplit.equals( tabUserServiceDto.getToken() ) );

        LocalDateTime currentDateTime = LocalDateTime.now();

        log.info("Last login server : {}", tabUserServiceDto.getLastLogin());
        log.info("Last connect server : {}", tabUserServiceDto.getLastConnectServer());
        log.info("Current time : {}", currentDateTime);

        boolean exceedTokenLifetimeAfterLogin =   currentDateTime.isAfter ( tabUserServiceDto.getLastLogin().plus(8, ChronoUnit.HOURS) );
        boolean exceedLastConnectServer =  currentDateTime.isAfter( tabUserServiceDto.getLastConnectServer().plus(5, ChronoUnit.MINUTES));

        log.info("correctToken : {}", correctToken);
        log.info("exceedTokenLifetimeAfterLogin : {}", exceedTokenLifetimeAfterLogin);
        log.info("exceedLastConnectServer : {}", exceedLastConnectServer);

        return correctToken && !exceedTokenLifetimeAfterLogin && !exceedLastConnectServer;

    }

    @Override
    public void updateLastConnectServer(String tokenFromRequest) {

        String[] splitToken = tokenFromRequest.split(":");
        String uid = splitToken[0];

        TabUserService.TabUserServiceDto tabUserServiceDto = tabUserService.getById(uid);

        if (tabUserServiceDto == null) {
            return;
        }

        long lifetimeCacheInSec = 600;
        boolean expireCache = tabUserServiceDto.getLastConnectServer().plusSeconds(lifetimeCacheInSec).isAfter(LocalDateTime.now());

        if (expireCache) {
            // ทำการ update last connect server เพื่อให้มันไป update ลง Database
            tabUserServiceDto.setLastConnectServer(LocalDateTime.now());
            tabUserService.updateTabUser(new TabUserService.TabUserServiceDto());
        } else {
            // ไม่ต้องทำอะไร
        }

    }
}
