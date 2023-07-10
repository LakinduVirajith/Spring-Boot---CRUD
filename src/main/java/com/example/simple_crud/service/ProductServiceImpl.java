package com.example.simple_crud.service;

import com.example.simple_crud.entity.Product;
import com.example.simple_crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> fetchAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product fetchProductById(Long productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Product productValue = productRepository.findById(productId).get();
        if(Objects.nonNull(product.getProductName()) && !"".equalsIgnoreCase(product.getProductName())){
            productValue.setProductName(product.getProductName());
        }

        if(Objects.nonNull(product.getProductDescription()) && !"".equalsIgnoreCase(product.getProductDescription())){
            productValue.setProductDescription(product.getProductDescription());
        }

        if(Objects.nonNull(product.getRegularPrice())){
            productValue.setRegularPrice(product.getRegularPrice());
        }

        if(Objects.nonNull(product.getDiscount())){
            productValue.setDiscount(product.getDiscount());
        }
        return productRepository.save(productValue);
    }

    @Override
    public Product fetchProductByName(String productName) {
        return productRepository.findByProductName(productName);
    }

    public Product fetchProductByNameIgnoreCase(String productName) {
        return productRepository.findByProductNameIgnoreCase(productName);
    }
}
