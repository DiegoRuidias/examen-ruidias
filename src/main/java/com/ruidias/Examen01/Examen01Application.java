package com.ruidias.Examen01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Examen01Application {

	public static void main(String[] args) {
		SpringApplication.run(Examen01Application.class, args);
	}

}
