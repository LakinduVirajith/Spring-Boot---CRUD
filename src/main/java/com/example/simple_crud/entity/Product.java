package com.example.simple_crud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long productID;
    @NotBlank(message = "product name can't be null")
    private String productName;
    private String productDescription;
    @Positive
    private Double regularPrice;
    private Double discount;
}
