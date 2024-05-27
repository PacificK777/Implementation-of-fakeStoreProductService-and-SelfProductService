package com.example.projectbyumang.Service;

import com.example.projectbyumang.Models.Product;

public interface ProductService {

    Product getProductById(Long id);
    Product createAProduct(String title,
                           double price,
                           String description,
                           String imageUrl,
                           String category);

}
