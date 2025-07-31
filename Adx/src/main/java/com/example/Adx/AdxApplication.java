package com.example.Adx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdxApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdxApplication.class, args);
		System.out.println("Adx Server is Running on port 8081...");
	}
}