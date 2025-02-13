package com.example.authentication.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

@Accessors(chain = true)
@Data
@Slf4j
public class UserBaseAuthenticationFilter implements Filter {

    HashMap<String, String> mapUsernamePassword = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        mapUsernamePassword.put("belle", "belle_password");
        mapUsernamePassword.put("pat", "pat_password");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String username = httpServletRequest.getHeader("user");
        String password = httpServletRequest.getHeader("pass");


        String passwordInDb = mapUsernamePassword.get(username);

        if (passwordInDb == null || !Objects.equals(passwordInDb, password) ) {
            httpServletResponse.setStatus(401);
            httpServletResponse.getWriter().write("Authentication fail");
        } else {
            log.info("username : {} use server", username);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }


}
