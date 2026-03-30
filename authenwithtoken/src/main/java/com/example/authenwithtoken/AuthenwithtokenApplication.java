package com.example.authenwithtoken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AuthenwithtokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenwithtokenApplication.class, args);
	}

}
