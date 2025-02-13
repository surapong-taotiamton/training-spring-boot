package com.example.authentication.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Accessors(chain = true)
@Data
@Slf4j
public class AuthenticationFilter implements Filter {

    public static final String AUTHENTICATION_CODE = "BELLE";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String authenticationCode = httpServletRequest.getHeader("authenticationCode");

        if (Objects.equals(AUTHENTICATION_CODE, authenticationCode)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpServletResponse.setStatus(401);
            httpServletResponse.getWriter().write("Authentication fail");
        }

    }
}
