package com.example.demo.service;

import com.example.demo.entity.shop.Item;
import com.example.demo.repository.shop.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImplTest {
    @Mock
    private ItemRepository repository;

    @Mock
    private ItemService service;

    @Before
    public void setup() {
        service = new ItemServiceImpl(repository);
    }

    @Test
    public void findLatestSix_shouldLimitNumberOfResults() throws Exception {
        List<Item> items = Arrays.asList(new Item(), new Item(), new Item(), new Item(), new Item(), new Item(), new Item());

        when(repository.findAllByOrderByIdDesc()).thenReturn(items);

        assertEquals("findLatestSix should return up to 6 items", 6, service.findLatestSix().size());
        verify(repository).findAllByOrderByIdDesc();
    }
}