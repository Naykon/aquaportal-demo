package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CartDto {
    private LocalDateTime date;
    private List<ItemDto> items;
    private String username;
    private String deliveryAddress;
}
