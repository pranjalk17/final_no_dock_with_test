package com.pranjal.intuit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class CompanyMicroService {

    public static void main(String[] args) {
        System.out.println("--------------company 1--------------------J");
        SpringApplication.run(CompanyMicroService.class, args);
    }

}