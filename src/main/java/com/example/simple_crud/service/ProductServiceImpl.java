package com.example.simple_crud.service;

import com.example.simple_crud.entity.Product;
import com.example.simple_crud.error.ProductNotFoundException;
import com.example.simple_crud.repository.ProductRepository;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> fetchAllProducts() throws ProductNotFoundException {
        List<Product> products = productRepository.findAll();
        if(products.size() == 0){
            throw new ProductNotFoundException("Products Data Not Found");
        }
        return products;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product fetchProductById(Long productId) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if(!product.isPresent()){
            throw new ProductNotFoundException("Product Not Found");
        }
        return product.get();
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
    public Product fetchProductByName(String productName) throws ProductNotFoundException {
        Product product = productRepository.findByProductName(productName);
        if (!Objects.nonNull(product)) {
            product = productRepository.findByProductNameIgnoreCase(productName);
        }
        if(!Objects.nonNull(product)){
            throw new ProductNotFoundException("Product Not Found");
        }

        return product;
    }

    @Override
    public List<Product> searchProductsByName(String productName) throws ProductNotFoundException {
        String searchName = productName.trim().toLowerCase();
        List<Product> products = productRepository.findByProductNameIgnoreCaseContaining(searchName);
        if(products.size() == 0){
            throw new ProductNotFoundException("Search Products Data Not Found");
        }

        return products;
    }
}
