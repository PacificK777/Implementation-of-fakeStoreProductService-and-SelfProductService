package com.example.projectbyumang.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private Double price;
    private String image;

    @ManyToOne
    private Category category;

    @OneToOne
    private Rating rating;
}
