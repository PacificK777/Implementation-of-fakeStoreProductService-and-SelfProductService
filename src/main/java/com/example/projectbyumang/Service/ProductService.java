package com.example.projectbyumang.Service;

import com.example.projectbyumang.Exception.ProductNotFoundException;
import com.example.projectbyumang.Models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id) throws ProductNotFoundException;

    Product createAProduct(String title,
                           double price,
                           String description,
                           String imageUrl,
                           String category);

    List<Product> getAllProducts();

    Product deleteProductById(Long id) throws ProductNotFoundException;

    List<Product> getProductsByCategory(String category) throws ProductNotFoundException;

    List<String> getAllCategories();

    Product updateProduct(Long id,
                          String title,
                          String description,
                          String category,
                          double price,
                          String image) throws ProductNotFoundException;

    String generateRandomProducts();

    Page<Product> getPaginatedProduct(Integer pageSize, Integer pageNo);
}
