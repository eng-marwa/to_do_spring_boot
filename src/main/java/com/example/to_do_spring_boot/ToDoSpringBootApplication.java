package com.example.to_do_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ToDoSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoSpringBootApplication.class, args);
	}

}
