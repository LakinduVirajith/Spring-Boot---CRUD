package com.example.simple_crud.service;

import com.example.simple_crud.entity.Product;
import com.example.simple_crud.error.InternalServerErrorException;
import com.example.simple_crud.error.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<Product> fetchAllProducts() throws NotFoundException, InternalServerErrorException;

    Product saveProduct(Product product) throws InternalServerErrorException;

    Product fetchProductById(Long id) throws NotFoundException, InternalServerErrorException;

    void deleteProduct(Long productId) throws NotFoundException, InternalServerErrorException;

    Product updateProduct(Long productId, Product product) throws InternalServerErrorException, NotFoundException;

    Product fetchProductByName(String productName) throws NotFoundException, InternalServerErrorException;

    List<Product> searchProductsByName(String productName) throws NotFoundException, InternalServerErrorException;

    Page<Product> searchProductByProductDescription(String productDescription, Pageable pageable) throws NotFoundException, InternalServerErrorException;
}
