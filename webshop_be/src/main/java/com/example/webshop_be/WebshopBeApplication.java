package com.example.webshop_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class WebshopBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebshopBeApplication.class, args);
	}

}
