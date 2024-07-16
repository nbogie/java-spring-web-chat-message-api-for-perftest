package com.example.myapiwithspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//This annotation adds at least @Configuration, @EnableAutoConfiguration, @ComponentScan, and @EnableWebMvc
@SpringBootApplication
public class MyApiWithSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApiWithSpringBootApplication.class, args);
    }

}
