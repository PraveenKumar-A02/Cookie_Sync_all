package com.example.Fluct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FluctApplication {

	public static void main(String[] args) {
		SpringApplication.run(FluctApplication.class, args);
		System.out.println("Fluct Server is Running on port 8082...");
	}
}