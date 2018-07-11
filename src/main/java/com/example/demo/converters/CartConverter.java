package com.example.demo.converters;

import com.example.demo.entity.shop.Cart;
import com.example.demo.model.dto.CartDto;
import com.example.demo.model.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartConverter {

    private final ItemConverter itemConverter;

    @Autowired
    public CartConverter(ItemConverter itemConverter) {
        this.itemConverter = itemConverter;
    }

    public CartDto cartToCartDto(Cart cart){
        CartDto cartDto = new CartDto();

        List<ItemDto> items = itemConverter.itemToItemDto(cart.getItemQty());

        String address = cart.getDeliveryAddress();

        if (address == null) {
            //ToDo: Add proper validation so this is not needed
            cartDto.setDeliveryAddress("No address specified");
        } else {
            cartDto.setDeliveryAddress(address);
        }

        cartDto.setDate(cart.getCreationDate());
        cartDto.setUsername(cart.getUser().getUsername());
        cartDto.setItems(items);

        return cartDto;
    }
}
