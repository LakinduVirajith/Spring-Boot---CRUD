package com.example.simple_crud.service;

import com.example.simple_crud.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> fetchAllProducts();

    public Product saveProduct(Product product);

    public Product fetchProductById(Long id);
}
