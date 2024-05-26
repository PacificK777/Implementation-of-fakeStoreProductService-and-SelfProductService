package com.example.projectbyumang.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private String title;
    private String description;
    private double price;
    private String imageURL;
    private Category category;
    private Rating rating;
}
