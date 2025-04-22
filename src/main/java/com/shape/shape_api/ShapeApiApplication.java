package com.shape.shape_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.shape.shape_api")
public class ShapeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShapeApiApplication.class, args);
	}

}
