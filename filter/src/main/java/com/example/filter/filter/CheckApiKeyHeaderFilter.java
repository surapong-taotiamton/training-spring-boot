package com.example.filter.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class CheckApiKeyHeaderFilter implements Filter {

    public CheckApiKeyHeaderFilter(String correctApiKey) {
        this.correctApiKey = correctApiKey;
    }

    private String correctApiKey;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        log.info("IN doFilter of CheckApiKeyHeaderFilter correctApiKey : {}", correctApiKey);

        String inputApiKey = httpServletRequest.getHeader("api-key");

        if (correctApiKey.equals(inputApiKey)) {
            log.info("Case correct api key");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            log.info("Case incorrect api key");
            httpServletResponse.setStatus(500);
            httpServletResponse.getWriter().println("Incorrect api key");
        }
    }
}
