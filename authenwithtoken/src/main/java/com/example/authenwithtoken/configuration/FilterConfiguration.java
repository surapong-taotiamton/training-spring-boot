package com.example.authenwithtoken.configuration;

import com.example.authenwithtoken.filter.AuthenticationFilter;
import com.example.authenwithtoken.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class FilterConfiguration {
    private final AuthenticationService authenticationService;

    @Bean
    FilterRegistrationBean<AuthenticationFilter> authenticationFilterFilterRegistrationBean() {
        FilterRegistrationBean<AuthenticationFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter( new AuthenticationFilter(authenticationService) );
        filterFilterRegistrationBean.addUrlPatterns("/api/*");
        filterFilterRegistrationBean.setOrder(1);
        return filterFilterRegistrationBean;
    }

}
