package com.example.SimpleCRUDThymeleaf.Security;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MVCConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("login");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/index").setViewName("index");
	}
}
