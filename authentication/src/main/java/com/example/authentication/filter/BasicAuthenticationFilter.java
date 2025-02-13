package com.example.authentication.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;

@Accessors(chain = true)
@Data
@Slf4j
public class BasicAuthenticationFilter implements Filter {

    HashMap<String, String> mapUsernamePassword = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        mapUsernamePassword.put("belle", "belle_password");
        mapUsernamePassword.put("pat", "pat_password");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpServletRequest.getHeader("Authorization");

        log.info("{}",authHeader);

        if (authHeader != null && authHeader.startsWith("Basic ")) {
            // Extract Base64-encoded credentials
            String base64Credentials = authHeader.substring("Basic ".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);

            log.info("Data after decode64 : {}", credentials);

            // Split username and password
            String[] values = credentials.split(":", 2);
            if (values.length == 2) {
                String username = values[0];
                String password = values[1];

                String passwordInDb = mapUsernamePassword.get(username);

                if (passwordInDb == null || !Objects.equals(passwordInDb, password) ) {
                    httpServletResponse.setStatus(401);
                    httpServletResponse.getWriter().write("Authentication fail");
                } else {
                    log.info("username : {} use server", username);
                    filterChain.doFilter(servletRequest, servletResponse);
                }


            } else {
                httpServletResponse.setStatus(401);
                httpServletResponse.getWriter().write("Authentication fail");
            }
        } else {
            httpServletResponse.setStatus(401);
            httpServletResponse.getWriter().write("Authentication fail");
        }
    }


}
