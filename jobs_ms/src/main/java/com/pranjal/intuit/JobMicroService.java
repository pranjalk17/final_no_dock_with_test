package com.pranjal.intuit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EntityScan("com.pranjal.intuit.model")
public class JobMicroService {

	public static void main(String[] args) {
		System.out.println("----------------------------------J");
		SpringApplication.run(JobMicroService.class, args);
	}

}
