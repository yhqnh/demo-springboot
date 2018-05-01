package com.yhq.demospringboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DemoSpringBootApplication {

	public static void main(String[] args) {
		log.debug("this is debug message================");
		log.info("this is info message================");
		log.error("this is error message================");
		SpringApplication.run(DemoSpringBootApplication.class, args);
	}
}
