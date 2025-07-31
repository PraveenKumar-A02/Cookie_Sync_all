package com.example.Mro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MroApplication {

	public static void main(String[] args) {
		SpringApplication.run(MroApplication.class, args);
		System.out.println("MRO Server is Running on port 8080...");
	}
}