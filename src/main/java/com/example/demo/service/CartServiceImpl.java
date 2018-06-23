package com.example.demo.service;

import com.example.demo.entity.shop.Cart;
import com.example.demo.entity.shop.OrderStatus;
import com.example.demo.model.dto.CartDto;
import com.example.demo.repository.shop.CartRepository;
import com.example.demo.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveOrder(CartDto incomingOrder) {
        Cart cart = new Cart();
        cart.setItemQty(incomingOrder.getItemQty());
        cart.setUser(userRepository.findByUsername(incomingOrder.getUsername()));
        cart.setDeliveryAddress(incomingOrder.getDeliveryAddress());

        cart.setStatus(OrderStatus.ACTIVE);
        cart.setCreationDate(LocalDateTime.now());

        cartRepository.save(cart);
    }
}
