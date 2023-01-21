package com.nikola.vjezba6;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({ "com.nikola.*" })
public class Vjezba6Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Vjezba6Application.class, args);
	}

}
