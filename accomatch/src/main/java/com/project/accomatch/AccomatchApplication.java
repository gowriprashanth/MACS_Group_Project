package com.project.accomatch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AccomatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccomatchApplication.class, args);
	}

	public static final Logger logger = (Logger) LogManager.getLogger();
	@GetMapping("/get")
	public String getString(){
		logger.info("This is the first log");
		return "String";

	}

}
