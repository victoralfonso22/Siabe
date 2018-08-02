package com.siabe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SiabeApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SiabeApplication.class, args);
	}
	
	// Override the configure method from the SpringBootServletInitializer class
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SiabeApplication.class);
    }
}
