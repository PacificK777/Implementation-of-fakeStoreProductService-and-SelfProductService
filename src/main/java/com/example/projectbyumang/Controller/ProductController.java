package com.example.projectbyumang.Controller;

import com.example.projectbyumang.DTOS.ProductResponseDTO;
import com.example.projectbyumang.DTOS.RequestBodyProductDTO;
import com.example.projectbyumang.Exception.ProductNotFoundException;
import com.example.projectbyumang.Models.Product;
import com.example.projectbyumang.Service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        if(id == null){
            throw new ProductNotFoundException("Product id cannot be null");
        }
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    public ProductResponseDTO createAProduct(@RequestBody RequestBodyProductDTO request) {
        Product product = productService.createAProduct(
                request.getTitle(),
                request.getPrice(),
                request.getDescription(),
                request.getImage(),
                request.getCategory()
        );

        // Convert Product to ProductResponseDTO
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setId(product.getId());
        responseDTO.setTitle(product.getTitle());
        responseDTO.setPrice(product.getPrice());
        responseDTO.setDescription(product.getDescription());
        responseDTO.setImage(product.getImage());
        responseDTO.setCategory(product.getCategory().getCatTitle());

        return responseDTO;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/products/{id}")
    public Product deleteProductById(@PathVariable("id") Long id) {
        return productService.deleteProductById(id);
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getAllProductsByCategory(@PathVariable("category") String category){
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/products/categories")
    public List<String> getAllCategories(){
        return productService.getAllCategories();
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody RequestBodyProductDTO request){
            return productService.updateProduct(id,
                                                request.getTitle(),
                                                request.getDescription(),
                                                request.getImage(),
                                                request.getPrice(),
                                                request.getCategory());
    }
}
