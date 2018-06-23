package com.example.demo.service;

import com.example.demo.entity.shop.Item;
import com.example.demo.repository.shop.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> findLatestSix() {
        List<Item> latestItems = itemRepository.findAllByOrderByIdDesc().stream().limit(6).collect(Collectors.toList());
        return latestItems;
    }

    @Override
    public Double getItemPrice(String name) {
        return itemRepository.findItemByName(name).getPrice();
    }
}
