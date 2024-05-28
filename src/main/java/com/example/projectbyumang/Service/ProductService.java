package com.example.projectbyumang.Service;

import com.example.projectbyumang.Models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);

    Product createAProduct(String title,
                           double price,
                           String description,
                           String imageUrl,
                           String category);

    List<Product> getAllProducts();

    Product deleteProductById(Long id);

    List<Product> getProductsByCategory(String category);

    List<String> getAllCategories();

    Product updateProduct(Long id,
                          String title,
                          String description,
                          String category,
                          double price,
                          String image);

}
