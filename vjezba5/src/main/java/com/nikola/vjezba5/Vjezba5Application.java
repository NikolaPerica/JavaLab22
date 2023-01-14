package com.nikola.vjezba5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.nikola.*" })
public class Vjezba5Application {
	

	public static void main(String[] args) {
		SpringApplication.run(Vjezba5Application.class, args);
	}

}
