package com.example.simple_crud.service;

import com.example.simple_crud.entity.Product;
import com.example.simple_crud.error.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    List<Product> fetchAllProducts() throws ProductNotFoundException;

    Product saveProduct(Product product);

    Product fetchProductById(Long id) throws ProductNotFoundException;

    void deleteProduct(Long productId);

    Product updateProduct(Long productId, Product product);

    Product fetchProductByName(String productName) throws ProductNotFoundException;

    List<Product> searchProductsByName(String productName) throws ProductNotFoundException;
}
