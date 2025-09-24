package com.example.authenwithtoken.filter;

import com.example.authenwithtoken.service.AuthenticationService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {

    private final AuthenticationService authenticationService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String token = httpServletRequest.getHeader("token");

        boolean successToken = authenticationService.verifyToken(token);

        if (successToken) {
            authenticationService.updateLastConnectServer(token);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpServletResponse.setStatus(401);
            httpServletResponse.getWriter().print("Unauthorized");
        }

    }
}
