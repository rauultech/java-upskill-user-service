package com.example.user_service.services;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.user_service.client.ProductClient;
import com.example.user_service.dto.ApiResponse;
import com.example.user_service.dto.ProductDto;
import com.example.user_service.entity.User;
import com.example.user_service.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProductClient productClient;
    
    public UserService(UserRepository userRepository, ProductClient productClient) {
        this.userRepository = userRepository;
        this.productClient = productClient;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public ApiResponse<Map<String, Object>> getAllProducts(int page, int size) {

        Map<String, Object> products = productClient.getAllProducts(page, size);
        
        return new ApiResponse<>(
            true,
            "Products fetched successfully",
            products
        );
    }
}