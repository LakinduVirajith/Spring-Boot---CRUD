package com.example.simple_crud.repository;

import com.example.simple_crud.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Product product = Product.builder().
                productName("Iphone x").
        productDescription("Original Apple iPhone X Factory Unlocked 99% New Mobile Phone 64GB/256GB ROM 5.8").
        regularPrice(75000.00).
        discount(12.50).build();

        entityManager.persistAndFlush(product);
        entityManager.flush();
    }

    @Test
    @DisplayName("Product Repository FindById")
    public void whenFindById_thenReturnProduct(){
        Product product = productRepository.findById(1L).orElse(null);

        assertNotNull(product);
        assertEquals("Iphone x", product.getProductName());
    }
}
