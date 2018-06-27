package com.example.demo.controller;

import com.example.demo.entity.shop.Category;
import com.example.demo.entity.shop.Item;
import com.example.demo.service.ItemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ItemsControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private ItemsController controller;

    @Mock
    private ItemService service;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void latestItems_shouldReturnAValidList  () throws Exception {
        List<Category> categories = Arrays.asList(new Category("cat 1"));
        List<Item> items = Arrays.asList(
                new Item("item1", "description1", "http://", 2.51, categories),
                new Item("item1", "description1", "http://", 2.51, categories),
                new Item("item1", "description1", "http://", 2.51, categories)
                );

        when(service.findLatestSix()).thenReturn(items);

        mockMvc.perform(get("/items/latest").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        verify(service).findLatestSix();
    }

    @Test
    public void itemPrice_ReturnsValidOutput() throws Exception {
        Item item = new Item("item1", "description1", "http://", 2.51, null);

        when(service.getItemPrice("item1")).thenReturn(item.getPrice());

        mockMvc.perform(get("/items/price" + "/item1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", equalTo(item.getPrice())));
    }
}