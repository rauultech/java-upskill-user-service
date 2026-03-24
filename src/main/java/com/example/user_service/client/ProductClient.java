package com.example.user_service.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.user_service.dto.ProductDto;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductClient {

    @GetMapping("/products/list")
    Map<String, Object> getAllProducts(
        @RequestParam("page") int page,
        @RequestParam("size") int size
    );
}
