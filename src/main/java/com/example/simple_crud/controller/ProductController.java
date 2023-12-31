package com.example.simple_crud.controller;

import com.example.simple_crud.entity.Product;
import com.example.simple_crud.error.InternalServerErrorException;
import com.example.simple_crud.error.NotFoundException;
import com.example.simple_crud.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public List<Product> fetchAllProducts() throws NotFoundException, InternalServerErrorException {
        LOGGER.info("fetch all product data");
        return productService.fetchAllProducts();
    }

    @PostMapping("/product")
    public Product saveProduct(@Valid @RequestBody Product product) throws InternalServerErrorException {
        LOGGER.info("save product data");
        return productService.saveProduct(product);
    }

    @GetMapping("/product/{id}")
    public Product fetchProduct(@PathVariable("id") Long productId) throws NotFoundException, InternalServerErrorException {
        LOGGER.info("fetch product data using id");
        return productService.fetchProductById(productId);
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable("id") Long productId) throws NotFoundException, InternalServerErrorException {
        LOGGER.info("delete product data using id");
        productService.deleteProduct(productId);
        return "Product Deleted Successfully";
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@PathVariable("id") Long productId, @RequestBody Product product) throws InternalServerErrorException, NotFoundException {
        LOGGER.info("update product data using id");
        return productService.updateProduct(productId, product);
    }

    @GetMapping("/product/search/name/{name}")
    public Product fetchProductByName(@PathVariable("name") String productName) throws NotFoundException, InternalServerErrorException {
        LOGGER.info("search product data using product name");
        return productService.fetchProductByName(productName);
    }

    @GetMapping("product/search/{name}")
    public List<Product> fetchAllProductByNameIgnoreCase(@PathVariable("name") String productName) throws NotFoundException, InternalServerErrorException {
        LOGGER.info("search list of products data using product name");
        return productService.searchProductsByName(productName);
    }

    @GetMapping("product/search/description/{name}")
    public Page<Product> searchProductByProductDescription(@PathVariable("name") String productDescription, Pageable pageable) throws NotFoundException, InternalServerErrorException {
        LOGGER.info("search list of products data using product description");
        return productService.searchProductByProductDescription(productDescription, pageable);
    }
}
