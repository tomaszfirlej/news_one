package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewsOneApplication {

	private static final Logger log = LoggerFactory.getLogger(NewsOneApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(NewsOneApplication.class, args);
	}

}