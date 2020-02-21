package ru.cft.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.logging.LogManager;

@SpringBootApplication
public class HandBookServiceApplication {

	public static void main(String[] args) {
		try {
			LogManager.getLogManager().readConfiguration(
					HandBookServiceApplication.class.getResourceAsStream("/application.yml"));
		} catch (IOException e) {
			System.err.println("Could not setup logger configuration: " + e.toString());
		}
		SpringApplication.run(HandBookServiceApplication.class, args);
	}

}
