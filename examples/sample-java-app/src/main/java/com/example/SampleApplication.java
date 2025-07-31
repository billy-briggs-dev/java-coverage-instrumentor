package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot application demonstrating various code patterns
 * for testing coverage instrumentation.
 */
@SpringBootApplication
public class SampleApplication {
    
    public static void main(String[] args) {
        System.out.println("Starting Sample Spring Boot API...");
        SpringApplication.run(SampleApplication.class, args);
    }
}
