package com.example.ThreadingClouds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableScheduling
public class ThreadingCloudsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadingCloudsApplication.class, args);
    }

    @GetMapping("/")
    public String hello() {
        return "Hello world!";
    }

    @Scheduled(fixedDelay = 60000)
    public void logMessage() {
        System.out.println("Application running...");
    }
}

