package com.example.demo.controller;

import com.example.demo.entity.shop.Item;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController {
    private final ItemService itemService;

    @Autowired
    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "/latest", produces = "application/json")
    public List<Item> getLatestSix() {
        return itemService.findLatestSix();
    }

    @GetMapping("/price/{name}")
    public Double itemPrice(@PathVariable("name") String itemName) {
        return itemService.getItemPrice(itemName);
    }
}
