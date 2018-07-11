package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

//ToDo switch over to CartDto for new posted orders
@Getter
@Setter
@Deprecated
public class CartDtoOld {
    String username;

    private Map<String, Integer> itemQty;

    private String deliveryAddress;

}
