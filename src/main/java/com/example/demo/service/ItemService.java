package com.example.demo.service;

import com.example.demo.entity.shop.Item;

import java.util.List;

public interface ItemService {
    List<Item> findLatestSix();
}
