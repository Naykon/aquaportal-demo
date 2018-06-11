package com.example.demo.config;

import com.example.demo.entity.shop.Category;
import com.example.demo.entity.shop.Item;
import com.example.demo.repository.shop.ItemRepository;
import com.example.demo.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Role role = new Role("ROLE_USER");
//        User user = new User("Niki", "Nikolaev",
//                "admin", "admin", true, "abv@abv.bg", role);
//
//        userRepository.save(user);


        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Cat 1"));

        Item item = new Item("Nola", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu imperdiet ullamcorper. Duis at lacus. Quisque purus sapien,", "http://placehold.it/700x400", 0.08, categories);
        itemRepository.save(item);
    }
}
