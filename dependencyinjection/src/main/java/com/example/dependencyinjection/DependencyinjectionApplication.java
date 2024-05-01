package com.example.dependencyinjection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
@SpringBootApplication
@ComponentScan( excludeFilters = {
		@ComponentScan.Filter( type = FilterType.REGEX, pattern = "com.example.dependencyinjection.notscan.*"),
})
public class DependencyinjectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DependencyinjectionApplication.class, args);
	}

}
