package com.example.demo.entity.shop;

import com.example.demo.entity.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

//    @ManyToMany(cascade = CascadeType.PERSIST)
//    @JoinTable (name = "order_item",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "item_id"))
//    private List<Item> items;

    @ElementCollection
    private Map<String, Integer> itemQty;

    private LocalDateTime creationDate;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    private String deliveryAddress;

    public Cart() {
        creationDate = LocalDateTime.now();
    }
}
