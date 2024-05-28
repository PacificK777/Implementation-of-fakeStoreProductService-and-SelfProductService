package com.example.projectbyumang.DTOS;

import com.example.projectbyumang.Models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

    // Method to convert ProductResponseDTO to Product
    public Product requestProduct() {
        Product product = new Product();
        product.setId(this.id);
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setCategory(this.category);
        product.setPrice(this.price);
        product.setImage(this.image);
        return product;
    }
}

