package com.example.projectbyumang.Service;
import com.example.projectbyumang.Models.Category;
import com.example.projectbyumang.Models.Product;
import com.example.projectbyumang.Repositories.CategoryRepository;
import com.example.projectbyumang.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{
     private ProductRepository productRepository;
     private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product getProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Product createAProduct(String title,
                                  double price,
                                  String description,
                                  String imageUrl,
                                  String category) {
        Product newProduct = new Product();
        newProduct.setImage(imageUrl);
        newProduct.setTitle(title);
        newProduct.setPrice(price);
        newProduct.setDescription(description);

        Category categoryFromDatabase = categoryRepository.findCategoryByCatTitle(category);
        if (categoryFromDatabase == null) {
            Category category1 = new Category();
            category1.setCatTitle(category);
            categoryRepository.save(category1);
            categoryFromDatabase = category1;
        }
        newProduct.setCategory(categoryFromDatabase);
        return productRepository.save(newProduct);
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product deleteProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return List.of();
    }

    @Override
    public List<String> getAllCategories() {
        return List.of();
    }

    @Override
    public Product updateProduct(Long id,
                                 String title,
                                 String description,
                                 String category,
                                 double price,
                                 String image) {
        return null;
    }
}
