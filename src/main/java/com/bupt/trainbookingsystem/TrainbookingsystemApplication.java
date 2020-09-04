package com.bupt.trainbookingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TrainbookingsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainbookingsystemApplication.class, args);
    }

}
