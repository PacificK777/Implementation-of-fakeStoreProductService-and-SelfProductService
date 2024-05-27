package com.example.projectbyumang.Controller;

import com.example.projectbyumang.DTOS.ProductResponseDTO;
import com.example.projectbyumang.DTOS.RequestBodyProductDTO;
import com.example.projectbyumang.Models.Product;
import com.example.projectbyumang.Service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
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
        responseDTO.setCategory(product.getCategory());

        return responseDTO;
    }
}
