package com.example.demo.service;

import com.example.demo.model.dto.CartDto;

public interface CartService {
    void saveOrder(CartDto incomingOrder);
}
