package com.example.simple_crud.controller;

import com.example.simple_crud.entity.Product;
import com.example.simple_crud.service.ProductService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Product product;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void setUp() {
        product = Product.builder().
                productName("Iphone x").
                productDescription("Original Apple iPhone X Factory Unlocked 99% New Mobile Phone 64GB/256GB ROM 5.8").
                regularPrice(75000.00).
                discount(12.50).build();
    }

    @Test
    @SneakyThrows
    @DisplayName("Save Product Controller")
    void saveProduct() {
        Product inputProduct = product = Product.builder().
                productName("Iphone x").
                productDescription("Original Apple iPhone X Factory Unlocked 99% New Mobile Phone 64GB/256GB ROM 5.8").
                regularPrice(75000.00).
                discount(12.50).build();

        Mockito.when(productService.saveProduct(inputProduct)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/product").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"productName\": \"Iphone x\",\n" +
                        "    \"productDescription\": \"Original Apple iPhone X Factory Unlocked 99% New Mobile Phone 64GB/256GB ROM 5.8\",\n" +
                        "    \"regularPrice\": \"75000\",\n" +
                        "    \"discount\": \"12.50\"\n" +
                        "}")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @SneakyThrows
    @DisplayName("Fetch Product By ID")
    void fetchProductById() {
        Mockito.when(productService.fetchProductById(1L)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/1").
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$.productName").value(product.getProductName()));
    }
}