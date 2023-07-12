package com.example.simple_crud.service;

import com.example.simple_crud.entity.Product;
import com.example.simple_crud.repository.ProductRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        Product product1 = Product.builder()
                .productID(1L)
                .productName("Iphone x")
                .productDescription("Original Apple iPhone X Factory Unlocked 99% New Mobile Phone 64GB/256GB ROM 5.8")
                .regularPrice(75000.00)
                .discount(12.50).build();

        Product product2 = Product.builder()
                .productID(2L)
                .productName("Iphone xr")
                .productDescription("Original Apple iPhone Xr Factory Unlocked 99% New Mobile Phone 64GB/256GB ROM 5.8")
                .regularPrice(78000.00)
                .discount(15.00).build();

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Mockito.when(productRepository.findAll()).thenReturn(products);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product1));
        Mockito.when(productRepository.findById(3L)).thenReturn(Optional.empty());
        Mockito.when(productRepository.findByProductName("Iphone x")).thenReturn(product1);
        Mockito.when(productRepository.findByProductName("iphone x")).thenReturn(product1);
        Mockito.when(productRepository.findByProductName("Iphone xs")).thenReturn(null);
        Mockito.when(productRepository.findByProductNameIgnoreCaseContaining("Iphone")).thenReturn(products);
        Mockito.when(productRepository.findByProductNameIgnoreCaseContaining("iphone")).thenReturn(products);
        Mockito.when(productRepository.findByProductNameIgnoreCaseContaining("Watch")).thenReturn(null);
    }

    @Test
    @SneakyThrows
    @DisplayName("Get All Products")
    public void whenRequestAllListOfProducts_thenProductShouldFound(){
        List<Product> found = productService.fetchAllProducts();

        assertNotNull(found);
    }

    @Test
    @SneakyThrows
    @DisplayName("Get All Products Empty")
    public void whenRequestAllListOfProducts_thenShouldNotFindProducts(){
        List<Product> found = new ArrayList<>();
        Mockito.when(productRepository.findAll()).thenReturn(found);

        try {
            found = productService.fetchAllProducts();
        } catch (Exception ignored) {
            System.out.println("Exception occurred: " + ignored.getMessage());
        }

        assertEquals(found.size(), 0);
    }

    @Test
    @SneakyThrows
    @DisplayName("Get Product Based on Valid Product ID")
    public void whenValidProductId_thenProductShouldFound(){
        Long productId = 1L;
        Product found = productService.fetchProductById(productId);

        assertEquals(productId, found.getProductID());
    }

    @Test
    @SneakyThrows
    @DisplayName("Get Product Base on Invalid Product ID")
    public void whenInvalidProductId_thenShouldNotFindProductForInvalidId(){
        Long productId = 3L;
        Optional<Product> found = null;

        try {
            found = Optional.ofNullable(productService.fetchProductById(productId));
        } catch (Exception ignored) {
            System.out.println("Exception occurred: " + ignored.getMessage());
        }

        assertNull(found);
    }

    @Test
    @SneakyThrows
    @DisplayName("Get Product Based on Valid Product Name")
    public void whenValidProductName_thenProductShouldFound(){
        String productName = "Iphone x";
        Product found = productService.fetchProductByName(productName);

        assertEquals(productName, found.getProductName());
    }

    @Test
    @SneakyThrows
    @DisplayName("Get Product Based on Valid Product Name Ignore Case")
    public void whenValidProductName_thenProductShouldFoundIgnoreCase(){
        String productName = "iphone x";
        Product found = productService.fetchProductByName(productName);

        assertEquals(productName.toLowerCase(), found.getProductName().toLowerCase());
    }

    @Test
    @SneakyThrows
    @DisplayName("Get Product Base on Invalid Name")
    public void whenInvalidProductName_thenShouldNotFindProductForInvalidName(){
        String productName = "Iphone xs";
        Product found = null;

        try {
            found = productService.fetchProductByName(productName);
        } catch (Exception ignored) {
            System.out.println("Exception occurred: " + ignored.getMessage());
        }

        assertNull(found);
    }

    @Test
    @SneakyThrows
    @DisplayName("Get Product Base on Search Value")
    public void whenValidSearchValue_thenProductsShouldFound(){
        String searchValue = "Iphone";
        List<Product> products = productService.searchProductsByName(searchValue);

        // Assert that the list of products is not null and contains at least one product
        assertNotNull(products);
        assertFalse(products.isEmpty());

        for (Product product : products) {
            String productName = product.getProductName();
            assertTrue(productName.contains(searchValue));
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("Get Product Base on Search Value Ignore Case")
    public void whenValidSearchValue_thenProductsShouldFoundIgnoreCase(){
        String searchValue = "iphone";
        List<Product> products = productService.searchProductsByName(searchValue);

        // Assert that the list of products is not null and contains at least one product
        assertNotNull(products);
        assertFalse(products.isEmpty());

        for (Product product : products) {
            String productName = product.getProductName();
            assertTrue(productName.toLowerCase().contains(searchValue.toLowerCase()));
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("Get Product Base on Invalid Search Value")
    public void whenInvalidSearchValue_thenShouldNotFindProducts(){
        String searchValue = "Watch";
        List<Product> products = null;

        try {
            products = productService.searchProductsByName(searchValue);
        } catch (Exception ignored) {
            System.out.println("Exception occurred: " + ignored.getMessage());
        }

        assertNull(products);
    }
}