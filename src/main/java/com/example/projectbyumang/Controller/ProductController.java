package com.example.projectbyumang.Controller;

import com.example.projectbyumang.DTOS.ProductResponseDTO;
import com.example.projectbyumang.DTOS.RequestBodyProductDTO;
import com.example.projectbyumang.Exception.ProductNotFoundException;
import com.example.projectbyumang.Models.Product;
import com.example.projectbyumang.Service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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
        if (id == null) {
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
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/products/{id}")
    public Product deleteProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.deleteProductById(id);
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getAllProductsByCategory(@PathVariable("category") String category) throws ProductNotFoundException {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/products/categories")
    public List<String> getAllCategories() {
        return productService.getAllCategories();
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody RequestBodyProductDTO request) throws ProductNotFoundException {
        return productService.updateProduct(id,
                request.getTitle(),
                request.getDescription(),
                request.getImage(),
                request.getPrice(),
                request.getCategory());
    }

    @PostMapping("/products/generate")
    @ResponseBody
    public String generateRandomProducts() {
        return productService.generateRandomProducts();
    }

    //PAGINATION
    @GetMapping("/products/all/{pageNo}/{pageSize}")
    public List<Product> getPaginatedProductWithPageSize(@PathVariable("pageNo") Integer pageNo,
                                                         @PathVariable("pageSize") Integer pageSize) {
        Page<Product> productPage = productService.getPaginatedProduct(pageNo,pageSize);
        System.out.println("ProductPage: " + productPage);
        return productPage.getContent();
    }

    //TO DISPLAY 10 PRODUCTS PER PAGE
    @GetMapping("/products/all/default/{pageNo}")
    public List<Product> getPaginatedProductDefaultPageSize(@PathVariable("pageNo") Integer pageNo) {
        int pageSize = 10; // Set the number of products per page to 10
        Page<Product> productPage = productService.getPaginatedProduct(pageNo, pageSize);
        System.out.println("ProductPage: " + productPage);
        return productPage.getContent();
    }

    //TO DISPLAY 10 PRODUCTS PER PAGE IN DESC ORDER
    @GetMapping("/products/all/desc/{pageNo}")
    public List<Product> getPaginatedProductDesc(@PathVariable("pageNo") Integer pageNo) {
        int pageSize = 10; // Set the number of products per page to 10
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "price");
        Page<Product> productPage = productService.getPaginatedProduct(pageable);
        System.out.println("ProductPage: " + productPage);
        return productPage.getContent();
    }
}
