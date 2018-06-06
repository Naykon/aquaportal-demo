package com.example.demo.repository.shop;

import com.example.demo.entity.shop.Item;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
}
