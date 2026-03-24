package com.example.user_service.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductClientFallback implements ProductClient {

    @Override
    public Map<String, Object> getAllProducts(int page, int size) {
        Map<String, Object> fallbackResponse = new HashMap<>();

        fallbackResponse.put("content", new ArrayList<>());
        fallbackResponse.put("totalElements", 0);
        fallbackResponse.put("totalPages", 0);
        fallbackResponse.put("size", size);
        fallbackResponse.put("number", page);

        return fallbackResponse;
    }
    
}
