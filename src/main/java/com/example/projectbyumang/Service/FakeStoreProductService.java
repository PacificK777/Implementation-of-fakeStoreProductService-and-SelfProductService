package com.example.projectbyumang.Service;

import com.example.projectbyumang.DTOS.FakeStoreProductDTO;
import com.example.projectbyumang.Models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStore")
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
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
}
