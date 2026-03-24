package com.example.user_service.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE", fallback = ProductClientFallback.class)
public interface ProductClient {

    @GetMapping("/products/list")
    Map<String, Object> getAllProducts(
        @RequestParam("page") int page,
        @RequestParam("size") int size
    );
}
