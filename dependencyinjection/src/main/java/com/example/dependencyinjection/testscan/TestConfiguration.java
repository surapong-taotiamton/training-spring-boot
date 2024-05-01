package com.example.dependencyinjection.testscan;

import com.example.dependencyinjection.service.ManualService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    public TestConfiguration() {
        System.out.println("IN TestConfiguration");
    }


    @Bean
    public ManualService testManualService1() {
        System.out.println("IN testManualService1");
        return new ManualService("BELL", "A");
    }

    @Bean
    public ManualService testManualService2() {
        System.out.println("IN testManualService2");
        return new ManualService("PAT", "B");
    }

    @Bean("POP")
    public ManualService testManualService3() {
        System.out.println("IN testManualService2");
        return new ManualService("POP", "C");
    }

}
