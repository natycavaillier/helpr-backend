package com.api.helpr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelprApplication{

	public static void main(String[] args) {
		SpringApplication.run(HelprApplication.class, args);
		System.out.println("Servidor HelpR.soulcode.com Funcionando localhost:8080");
	}
}