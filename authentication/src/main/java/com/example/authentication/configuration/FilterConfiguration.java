package com.example.authentication.configuration;

import com.example.authentication.filter.AuthenticationFilter;
import com.example.authentication.filter.BasicAuthenticationFilter;
import com.example.authentication.filter.UserBaseAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
//    @Bean
//    FilterRegistrationBean<AuthenticationFilter> authenticationFilterFilterRegistrationBean() {
//        FilterRegistrationBean<AuthenticationFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
//        filterFilterRegistrationBean.setFilter( new AuthenticationFilter() );
//        filterFilterRegistrationBean.addUrlPatterns("/api/*");
//        filterFilterRegistrationBean.setOrder(1);
//        return filterFilterRegistrationBean;
//    }

//    @Bean
//    FilterRegistrationBean<UserBaseAuthenticationFilter> userBaseAuthenticationFilterFilterRegistrationBean() {
//        FilterRegistrationBean<UserBaseAuthenticationFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
//        filterFilterRegistrationBean.setFilter( new UserBaseAuthenticationFilter() );
//        filterFilterRegistrationBean.addUrlPatterns("/api/*");
//        filterFilterRegistrationBean.setOrder(1);
//        return filterFilterRegistrationBean;
//    }


//    @Bean
//    FilterRegistrationBean<BasicAuthenticationFilter> userBaseAuthenticationFilterFilterRegistrationBean() {
//        FilterRegistrationBean<BasicAuthenticationFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
//        filterFilterRegistrationBean.setFilter( new BasicAuthenticationFilter() );
//        filterFilterRegistrationBean.addUrlPatterns("/api/*");
//        filterFilterRegistrationBean.setOrder(1);
//        return filterFilterRegistrationBean;
//    }

}
