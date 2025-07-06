package com.example.authenwithtoken.service;

import com.example.authenwithtoken.entity.TabUser;
import com.example.authenwithtoken.repository.TabUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final TabUserRepository tabUserRepository;

    public String genToken(String username, String password) {
        TabUser tabUser = tabUserRepository.findByUsername(username).orElse(null);

        if (tabUser == null || !password.equals(tabUser.getPassword())) {
            return null;
        } else {
            String token = RandomStringUtils.secure().nextAlphanumeric(50);
            tabUser.setToken(token);
            tabUserRepository.save(tabUser);
            return String.format("%s:%s", tabUser.getUserId(), token);
        }
    }

    public boolean verifyToken(String tokenFromRequest) {

        if (tokenFromRequest == null) {
            return false;
        }

        String[] splitToken = tokenFromRequest.split(":");
        String uid = splitToken[0];
        String tokenAfterSplit = splitToken[1];

        TabUser tabUser = tabUserRepository.findById(uid).orElse(null);

        return tabUser != null && ( tokenAfterSplit != null && tokenAfterSplit.equals(tabUser.getToken()) );
    }

}
