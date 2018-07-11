package com.example.demo.converters;

import com.example.demo.model.dto.ItemDto;
import com.example.demo.repository.shop.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ItemConverter {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemConverter(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemDto> itemToItemDto(Map<String, Integer> itemQuantity) {
        List<ItemDto> itemsForDisplay = new ArrayList<>();

        for (String itemName : itemQuantity.keySet()) {
            ItemDto item = new ItemDto();
            item.setName(itemName);
            item.setQuantity(itemQuantity.get(itemName));
            item.setPrice(itemRepository.findItemByName(itemName).getPrice());
            itemsForDisplay.add(item);
        }

        return itemsForDisplay;
    }
}
