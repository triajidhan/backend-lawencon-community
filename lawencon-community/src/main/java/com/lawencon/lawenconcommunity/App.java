package com.lawencon.lawenconcommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
@EntityScan(basePackages = "com.lawencon")
@ComponentScan(basePackages = "com.lawencon")
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
