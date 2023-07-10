package com.example.simple_crud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long productID;
    @NotBlank(message = "product name can't be null")
    private String productName;
    private String productDescription;
    @Positive
    private Double regularPrice;
    @PositiveOrZero
    private Double discount;

    private Double avgRatings;

    public Product() {
    }

    public Product(String productName, String productDescription, Double regularPrice, Double discount, Double avgRatings) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.regularPrice = regularPrice;
        this.discount = discount;
        this.avgRatings = avgRatings;
    }

    public Product(Long productID, String productName, String productDescription, Double regularPrice, Double discount, Double avgRatings) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.regularPrice = regularPrice;
        this.discount = discount;
        this.avgRatings = avgRatings;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(Double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getAvgRatings() {
        return avgRatings;
    }

    public void setAvgRatings(Double avgRatings) {
        this.avgRatings = avgRatings;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", regularPrice=" + regularPrice +
                ", discount=" + discount +
                ", avgRatings=" + avgRatings +
                '}';
    }
}
