package com.example.projectbyumang.Repositories;

import com.example.projectbyumang.Models.Category;
import com.example.projectbyumang.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product, Long> {

    Product findProductById(Long id);

    List<Product> findProductsByCategory(Category categoryFromDatabase);
}
