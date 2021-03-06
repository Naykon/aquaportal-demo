package com.example.demo.controller;

import com.example.demo.model.dto.CartDto;
import com.example.demo.model.dto.CartDtoOld;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/checkout")
    @ResponseStatus(HttpStatus.CREATED)
    public void postItems(@RequestBody CartDtoOld cartDtoOld) {
        cartService.saveOrder(cartDtoOld);
    }

    @RequestMapping("/all")
    public List<CartDto> getAllOrders() {

        return cartService.getAllOrders();
    }
}
