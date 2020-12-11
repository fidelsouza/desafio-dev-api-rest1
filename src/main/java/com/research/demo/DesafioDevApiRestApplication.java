package com.research.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.research.demo.Controller")
@ComponentScan("com.research.demo.ServicesImpl")
@ComponentScan("com.research.demo.Services")
@EntityScan("com.research.demo.Entities")   
@EnableJpaRepositories("com.research.demo.Repository")
public class DesafioDevApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioDevApiRestApplication.class, args);
	}

}
