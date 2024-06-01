package com.example.projectbyumang.Repositories;

import com.example.projectbyumang.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long> {

    Product findProductById(Long id);
}
