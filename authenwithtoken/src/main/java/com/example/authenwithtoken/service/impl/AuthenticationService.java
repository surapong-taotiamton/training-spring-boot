package com.example.authenwithtoken.service.impl;

import com.example.authenwithtoken.entity.TabUser;
import com.example.authenwithtoken.repository.TabUserRepository;
import com.example.authenwithtoken.service.AuthenticationServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationServiceInterface {

    private final TabUserRepository tabUserRepository;

    @Override
    public String genToken(String username, String password) {
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

        TabUser tabUser = tabUserRepository.findById(uid).orElse(null);

        boolean correctToken = tabUser != null && ( tokenAfterSplit != null && tokenAfterSplit.equals(tabUser.getToken()) );

        LocalDateTime currentDateTime = LocalDateTime.now();

        log.info("Last login server : {}", tabUser.getLastLogin());
        log.info("Last connect server : {}", tabUser.getLastConnectServer());
        log.info("Current time : {}", currentDateTime);

        boolean exceedTokenLifetimeAfterLogin = tabUser != null && currentDateTime.isAfter ( tabUser.getLastLogin().plus(8, ChronoUnit.HOURS) );
        boolean exceedLastConnectServer = tabUser != null && currentDateTime.isAfter( tabUser.getLastConnectServer().plus(5, ChronoUnit.MINUTES));

        log.info("correctToken : {}", correctToken);
        log.info("exceedTokenLifetimeAfterLogin : {}", exceedTokenLifetimeAfterLogin);
        log.info("exceedLastConnectServer : {}", exceedLastConnectServer);

        return correctToken && !exceedTokenLifetimeAfterLogin && !exceedLastConnectServer;
    }

    @Override
    public void updateLastConnectServer(String tokenFromRequest) {
        String[] splitToken = tokenFromRequest.split(":");
        String uid = splitToken[0];
        TabUser tabUser = tabUserRepository.findById(uid).orElseThrow();
        tabUser.setLastConnectServer(LocalDateTime.now());
        tabUserRepository.save(tabUser);
    }

}
