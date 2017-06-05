package com.onewingsoft.corestudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CorestudioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorestudioApplication.class, args);
	}
}
