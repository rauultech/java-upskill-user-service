package com.example.user_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @GetMapping("/list")
    public List<String> getUsers() {
        return List.of("Alice", "Bob", "Charlie");
    }
    
}
