package com.example.demo.repository.shop;

import com.example.demo.entity.shop.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "orders")
public interface CartRepository extends JpaRepository<Cart, Long> {
}
