package com.example.projectbyumang.Repositories;

import com.example.projectbyumang.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category, Long> {
    Category findCategoryByCatTitle(String catTitle);
}

