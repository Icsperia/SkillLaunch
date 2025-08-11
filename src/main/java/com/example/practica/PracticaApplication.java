package com.example.practica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;




@EntityScan(basePackages = "com.example.practica.Entity")

@SpringBootApplication
public class PracticaApplication {
	public static void main(String[] args) {
		SpringApplication.run(PracticaApplication.class, args);
	}
}