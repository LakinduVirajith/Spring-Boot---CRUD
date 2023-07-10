package com.example.simple_crud.service;

import com.example.simple_crud.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> fetchAllProducts();

    Product saveProduct(Product product);

    Product fetchProductById(Long id);

    void deleteProduct(Long productId);

    Product updateProduct(Long productId, Product product);

    Product fetchProductByName(String productName);

    List<Product> searchProductsByName(String productName);
}
