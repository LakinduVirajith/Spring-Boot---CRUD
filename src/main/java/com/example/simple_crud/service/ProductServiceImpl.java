package com.example.simple_crud.service;

import com.example.simple_crud.entity.Product;
import com.example.simple_crud.error.InternalServerErrorException;
import com.example.simple_crud.error.NotFoundException;
import com.example.simple_crud.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> fetchAllProducts() throws NotFoundException, InternalServerErrorException {
        List<Product> products;
        try {
            products = productRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerErrorException("Failed to Fetch the Products.");
        }

        if(products.size() == 0){
            throw new NotFoundException("Products Data Not Found");
        }
        return products;
    }

    @Override
    public Product saveProduct(Product product) throws InternalServerErrorException {
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw new InternalServerErrorException("Failed to Save the Product");
        }
    }

    @Override
    public Product fetchProductById(Long productId) throws NotFoundException, InternalServerErrorException {
        Optional<Product> product;
        try {
            product = productRepository.findById(productId);
        } catch (Exception e) {
            throw new InternalServerErrorException("Failed to Fetch the Product");
        }

        if(!product.isPresent()){
            throw new NotFoundException("Product Not Found");
        }
        return product.get();
    }

    @Override
    public void deleteProduct(Long productId) throws NotFoundException, InternalServerErrorException {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product Not Found");
        }

        try {
            productRepository.deleteById(productId);
        } catch (Exception e) {
            throw new InternalServerErrorException("Failed to Delete the Product.");
        }
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws InternalServerErrorException, NotFoundException {
        Product productValue;
        try {
            productValue = productRepository.findById(productId).get();
        } catch (Exception e) {
            throw new NotFoundException("Product Not Found");
        }

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

        try {
            return productRepository.save(productValue);
        } catch (Exception e) {
            throw new InternalServerErrorException("Failed to Update the Product.");
        }
    }

    @Override
    public Product fetchProductByName(String productName) throws NotFoundException, InternalServerErrorException {
        Product product;
        try {
            product = productRepository.findByProductName(productName);
            if (!Objects.nonNull(product)) {
                product = productRepository.findByProductNameIgnoreCase(productName);
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Failed to Fetch the Product.");
        }

        if(!Objects.nonNull(product)){
            throw new NotFoundException("Product Not Found");
        }

        return product;
    }

    @Override
    public List<Product> searchProductsByName(String productName) throws NotFoundException, InternalServerErrorException {
        List<Product> products;
        try {
            String searchName = productName.trim().toLowerCase();
            products = productRepository.findByProductNameIgnoreCaseContaining(searchName);
        } catch (Exception e) {
            throw new InternalServerErrorException("Failed to Search the Product.");
        }

        if(products.size() == 0){
            throw new NotFoundException("Search Products Data Not Found");
        }

        return products;
    }

    @Override
    public Page<Product> searchProductByProductDescription(String productDescription, Pageable pageable) throws NotFoundException, InternalServerErrorException{
        Page<Product> products;

        try {
            products = productRepository.findAllByProductDescriptionContaining(productDescription, pageable);
        } catch (Exception e) {
            throw new InternalServerErrorException("Failed to Search the Product.");
        }

        if(products.isEmpty()){
            throw new NotFoundException("Search Products Data Not Found");
        }

        return products;
    }


}