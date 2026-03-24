package com.example.user_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.dto.ApiResponse;
import com.example.user_service.entity.User;
import com.example.user_service.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/add")
    public User save(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/list")
    public Page<User> getAllUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }
    
   @GetMapping("/products")
    public ApiResponse<Map<String, Object>> getAllProducts(Pageable pageable) {
        return userService.getAllProducts(
            pageable.getPageNumber(),
            pageable.getPageSize()
        );
    }
}