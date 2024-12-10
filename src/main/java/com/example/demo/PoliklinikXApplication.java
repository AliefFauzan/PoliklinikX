package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PoliklinikXApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoliklinikXApplication.class, args);
		Connection conn = null;
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
