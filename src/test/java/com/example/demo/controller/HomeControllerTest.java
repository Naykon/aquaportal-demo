package com.example.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class HomeControllerTest {
    private MockMvc mockMvc;
    private HomeController controller;

    @Before
    public void before() {
        controller = new HomeController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void home_ShouldRenderIndexView() throws Exception {
        mockMvc.perform(get("/")).andExpect(view().name("index"));
    }
}