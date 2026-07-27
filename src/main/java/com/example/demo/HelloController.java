package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Bienvenue dans Jenkins + Docker + Spring Boot !";
    }

    @GetMapping("/health")
    public String health() {
        return "Application is running";
    }
}