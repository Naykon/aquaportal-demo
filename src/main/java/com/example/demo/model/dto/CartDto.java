package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CartDto {
    String username;

    private Map<String, Integer> itemQty;

    private String deliveryAddress;

}
