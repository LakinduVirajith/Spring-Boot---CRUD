package com.example.simple_crud.controller;

import com.example.simple_crud.entity.Product;
import com.example.simple_crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public List<Product> fetchAllProducts(){
        return productService.fetchAllProducts();
    }

    @PostMapping("/product")
    public Product saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @GetMapping("/product/{id}")
    public Product fetchProduct(@PathVariable("id") Long productId){
        return productService.fetchProductById(productId);
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable("id") Long productId){
        productService.deleteProduct(productId);
        return "Product Deleted Successfully";
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@PathVariable("id") Long productId, @RequestBody Product product){
        return productService.updateProduct(productId, product);
    }
}
