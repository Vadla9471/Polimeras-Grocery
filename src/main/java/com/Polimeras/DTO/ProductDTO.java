package com.Polimeras.DTO;

import com.Polimeras.Entity.Products;
import lombok.Data;
import java.util.Base64;

@Data
public class ProductDTO {
    private long id;
    private String name;
    private String description;
    private double price;
    private String category;
    private int stockQuantity;
    private String imgUrl; // Base64
    private String imgType;

    public ProductDTO(Products p) {
        this.id = p.getId();
        this.name = p.getName();
        this.description = p.getDescription();
        this.price = p.getPrice();
        this.category = p.getCategory().name();
        this.stockQuantity = p.getStockQuantity();
        this.imgType = p.getImgType();
        this.imgUrl = Base64.getEncoder().encodeToString(p.getImgUrl());
    }

}
