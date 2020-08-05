package com.svilen.onlinebookstore.controller;

import com.svilen.onlinebookstore.web.HomeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

//@WebMvcTest(HomeController.class)
@SpringBootTest
public class HomeControllerTest {
    @Autowired
    private HomeController homeController;

    @Test
    public void contextLoad() throws Exception {
        assertThat(homeController).isNotNull();
    }


}
