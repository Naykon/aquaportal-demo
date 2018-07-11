package com.example.demo.service;

import com.example.demo.model.dto.CartDto;
import com.example.demo.model.dto.CartDtoOld;

import java.util.List;

public interface CartService {
    void saveOrder(CartDtoOld incomingOrder);
    List<CartDto> getAllOrders();
}
