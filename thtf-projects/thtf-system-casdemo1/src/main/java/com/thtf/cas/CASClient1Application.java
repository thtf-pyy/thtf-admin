package com.thtf.cas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.thtf")
@SpringBootApplication
public class CASClient1Application {

    public static void main(String[] args) {
        SpringApplication.run(CASClient1Application.class, args);
    }
}
