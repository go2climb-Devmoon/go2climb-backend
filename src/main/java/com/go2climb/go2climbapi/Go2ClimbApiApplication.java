package com.go2climb.go2climbapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Go2ClimbApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Go2ClimbApiApplication.class, args);
	}

}
