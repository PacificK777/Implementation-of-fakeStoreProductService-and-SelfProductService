package com.example.projectbyumang.Controller;

import com.example.projectbyumang.DTOS.FakeStoreProductDTO;
import com.example.projectbyumang.Models.Product;
import com.example.projectbyumang.Service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
        ProductService productService;

        public ProductController(ProductService productService) {
                this.productService = productService;
        }

    @GetMapping("/products/{id}")
        public Product getProductById(@PathVariable("id")Long id){
                return productService.getProductById(id);
        }
}
