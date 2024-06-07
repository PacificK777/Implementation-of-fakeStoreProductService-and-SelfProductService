package com.example.projectbyumang.Service;

import com.example.projectbyumang.DTOS.FakeStoreProductDTO;
import com.example.projectbyumang.DTOS.ProductResponseDTO;
import com.example.projectbyumang.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;



@Service("fakeStore")
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {

        if(id == 0) {
        throw new IllegalArgumentException("Invalid ProductId, try again !!");
        }

        FakeStoreProductDTO fakeStoreProductDTO =  restTemplate.getForObject
                ("https://fakestoreapi.com/products/"+ id,
                FakeStoreProductDTO.class);

        return fakeStoreProductDTO.toProduct();
    }

    @Override
    public Product createAProduct(String title,
                                  double price,
                                  String description,
                                  String imageUrl,
                                  String category) {

        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setImage(imageUrl);
        fakeStoreProductDTO.setTitle(title);
        fakeStoreProductDTO.setCategory(category);
        fakeStoreProductDTO.setDescription(description);
        fakeStoreProductDTO.setPrice(price);
        FakeStoreProductDTO fakeStoreProductDTO1 = restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStoreProductDTO, FakeStoreProductDTO.class);

        return fakeStoreProductDTO1.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        FakeStoreProductDTO[] fakeStoreProductDTOS = restTemplate
                .getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);
        for(FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOS){
                products.add(fakeStoreProductDTO.toProduct());
        }
        return products;
    }

    @Override
    public Product deleteProductById(Long id) {
        Product productToDelete = getProductById(id);
                restTemplate.delete("https://fakestoreapi.com/products/"+id);
                return productToDelete;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        List<Product> productsByCategory = new ArrayList<>();
        FakeStoreProductDTO[] fakeStoreProductDTOS =restTemplate
                .getForObject("https://fakestoreapi.com/products/category/"+category, FakeStoreProductDTO[].class);
        for(FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProductDTOS){
            productsByCategory.add(fakeStoreProductDTO.toProduct());
        }
        return productsByCategory;
    }

    @Override
    public List<String> getAllCategories() {
        String[] categoriesArray = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories", String[].class);

        List<String> categories = new ArrayList<>();
        for (String category : categoriesArray) {
            categories.add(category);
        }
        return categories;
    }

    @Override
    public Product updateProduct(Long id,
                                 String title,
                                 String description,
                                 String category,
                                 double price,
                                 String image) {
        // Get the existing product
        Product existingProduct = getProductById(id);

        if (existingProduct != null) {
            // Create productResponseDTO object and set values
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setId(id);
            productResponseDTO.setImage(image);
            productResponseDTO.setTitle(title);
            productResponseDTO.setCategory(category);
            productResponseDTO.setDescription(description);
            productResponseDTO.setPrice(price);


            // Use RestTemplate to send the update
            restTemplate.put("https://fakestoreapi.com/products/" + id,
                    productResponseDTO, ProductResponseDTO.class);

            // Return the updated product
            return productResponseDTO.requestProduct();
        }
        return null;
    }

    @Override
    public Page<Product> getPaginatedProduct(Integer pageSize, Integer pageNo) {
        return null;
    }

    @Override
    public String generateRandomProducts() {
        return null;
    }

}
