package com.example.projectbyumang.Service;
import com.example.projectbyumang.Exception.ProductNotFoundException;
import com.example.projectbyumang.Models.Category;
import com.example.projectbyumang.Models.Product;
import com.example.projectbyumang.Repositories.CategoryRepository;
import com.example.projectbyumang.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Product getProductById(Long id) throws ProductNotFoundException {
        Product product = productRepository.findProductById(id);
        if (product == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        return product;
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
        return productRepository.findAll();
    }

    @Override
    public Product deleteProductById(Long id) throws ProductNotFoundException {
        Product product = productRepository.findProductById(id);
        if (product == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        productRepository.delete(product);
        return product;
    }

    @Override
    public List<Product> getProductsByCategory(String category) throws ProductNotFoundException {
        Category categoryFromDatabase = categoryRepository.findCategoryByCatTitle(category);
        if (categoryFromDatabase == null) {
            throw new ProductNotFoundException("Category " + category + " not found");
        }
        return productRepository.findProductsByCategory(categoryFromDatabase);
    }

    @Override
    public List<String> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<String> categoryTitles = new ArrayList<>();
        for (Category category : categories) {
            categoryTitles.add(category.getCatTitle());
        }
        return categoryTitles;
    }

    @Override
    public Product updateProduct(Long id,
                                 String title,
                                 String description,
                                 String category,
                                 double price,
                                 String image) throws ProductNotFoundException {
        Product product = productRepository.findProductById(id);
        if (product == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImage(image);
        Category categoryFromDatabase = categoryRepository.findCategoryByCatTitle(category);
        if (categoryFromDatabase == null) {
            Category newCategory = new Category();
            newCategory.setCatTitle(category);
            categoryRepository.save(newCategory);
            categoryFromDatabase = newCategory;
        }
        product.setCategory(categoryFromDatabase);
        return productRepository.save(product);
    }
}
