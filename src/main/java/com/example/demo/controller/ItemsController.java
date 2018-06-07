package com.example.demo.controller;

import com.example.demo.entity.shop.Item;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemsController {
    private final ItemService itemService;

    @Autowired
    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "items/latest", produces = "application/json")
    public List<Item> getLatestSix() {
        return itemService.findLatestSix();
    }
}
