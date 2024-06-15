package com.example.filter.configuration;

import com.example.filter.filter.CheckApiKeyHeaderFilter;
import com.example.filter.filter.LogFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class FilerConfiguration {

    @Bean
    FilterRegistrationBean<CheckApiKeyHeaderFilter> secretFilterBean() {

        log.info("In create bean secretFilterBean");

        FilterRegistrationBean<CheckApiKeyHeaderFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter( new CheckApiKeyHeaderFilter("SECRET-PASSWORD") );
        filterFilterRegistrationBean.addUrlPatterns("/secret/*");
        filterFilterRegistrationBean.setOrder(1);
        return filterFilterRegistrationBean;
    }


    @Bean
    FilterRegistrationBean<CheckApiKeyHeaderFilter> topSecretFilterBean() {

        log.info("In create bean topSecretFilterBean");

        FilterRegistrationBean<CheckApiKeyHeaderFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter( new CheckApiKeyHeaderFilter("TOP-SECRET-PASSWORD") );
        filterFilterRegistrationBean.addUrlPatterns("/top-secret/*");
        filterFilterRegistrationBean.setOrder(1);
        return filterFilterRegistrationBean;
    }


    @Bean
    FilterRegistrationBean<LogFilter> logFilterBean() {

        log.info("In create bean logFilterBean");
        FilterRegistrationBean<LogFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LogFilter());
        filterFilterRegistrationBean.addUrlPatterns("/*");
        filterFilterRegistrationBean.setOrder(0);


        return filterFilterRegistrationBean;

    }

}
