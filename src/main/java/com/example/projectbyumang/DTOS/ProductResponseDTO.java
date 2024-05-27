package com.example.projectbyumang.DTOS;

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
}
