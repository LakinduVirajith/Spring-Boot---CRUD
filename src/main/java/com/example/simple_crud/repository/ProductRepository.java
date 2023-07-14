package com.example.simple_crud.repository;

import com.example.simple_crud.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductName(String productName);
    Product findByProductNameIgnoreCase(String productName);
    List<Product> findByProductNameIgnoreCaseContaining(String productName);
}
