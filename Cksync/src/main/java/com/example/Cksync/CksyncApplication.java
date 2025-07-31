package com.example.Cksync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CksyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(CksyncApplication.class, args);
		System.out.println("Cksync Server is Running on port 8083...");
	}
}