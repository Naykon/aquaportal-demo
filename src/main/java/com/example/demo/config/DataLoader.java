package com.example.demo.config;

import com.example.demo.entity.shop.Cart;
import com.example.demo.entity.shop.Category;
import com.example.demo.entity.shop.Item;
import com.example.demo.entity.shop.OrderStatus;
import com.example.demo.entity.user.Role;
import com.example.demo.entity.user.User;
import com.example.demo.repository.shop.CartRepository;
import com.example.demo.repository.shop.ItemRepository;
import com.example.demo.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataLoader implements ApplicationRunner {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, CartRepository cartRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");

        User admin = new User("Niki", "Nikolaev",
                "admin", "admin", true, "abv@abv.bg", adminRole);

        User user = new User("User", "User",
                "user123", "user", true, "abv2@abv.bg", userRole);

        if (userRepository.countAllByUsername("admin") < 1) {
            userRepository.save(admin);
            //userRepository.save(user);

            List<Category> categories = new ArrayList<>();
            categories.add(new Category("Cat 1"));
            categories.add(new Category("Cat 2"));

            Item item = new Item("Dori", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu imperdiet ullamcorper. Duis at lacus. Quisque purus sapien,", "http://placehold.it/700x400", 0.08, categories);
            itemRepository.save(item);

            List<Category> categories2 = new ArrayList<>();
            categories2.add(new Category("Cat 3"));
            categories2.add(new Category("Cat 4"));

            Item item2 = new Item("Nemo", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu imperdiet ullamcorper. Duis at lacus. Quisque purus sapien,", "http://placehold.it/700x400", 50.1, categories2);

            itemRepository.save(item2);

            Cart order = new Cart();

            Map<String, Integer> items = new HashMap<>();
            items.put("Nemo", 1);
            items.put("Dori", 2);

            order.setStatus(OrderStatus.ACTIVE);
            order.setUser(user);
            order.setItemQty(items);
            order.setDeliveryAddress("Nqkakyv adress");
            cartRepository.save(order);
        }
    }
}
