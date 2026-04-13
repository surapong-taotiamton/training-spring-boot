package com.example.authenwithtoken.service;

public interface AuthenticationServiceInterface {
    String genToken(String username, String password);

    boolean verifyToken(String tokenFromRequest);

    void updateLastConnectServer(String tokenFromRequest);
}
