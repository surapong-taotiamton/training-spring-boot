package com.example.authenwithtoken.controller;

import com.example.authenwithtoken.controller.dto.LoginRequest;
import com.example.authenwithtoken.controller.dto.LoginResponse;
import com.example.authenwithtoken.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationService authenticationService;

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse>  login(@RequestBody LoginRequest request) {
        String returnToken = authenticationService.genToken(request.getUsername(), request.getPassword());

        if (returnToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        return ResponseEntity.ok(new LoginResponse().setToken(returnToken));
    }


}
