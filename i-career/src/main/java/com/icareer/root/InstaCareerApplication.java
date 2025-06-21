package com.icareer.root;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.icareer.controller,com.icareer.service")
@EnableJpaRepositories("com.icareer.repository")
@EntityScan("com.icareer.entity")
public class InstaCareerApplication {
	public static void main(String[] args) {
		SpringApplication.run(InstaCareerApplication.class, args);
		System.out.println("Hello World !!");
	}
}