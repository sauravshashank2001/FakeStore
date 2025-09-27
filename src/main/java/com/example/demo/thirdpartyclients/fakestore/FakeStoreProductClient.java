package com.example.demo.thirdpartyclients.fakestore;

import com.example.demo.dtos.GenericProductDto;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.thirdpartyclients.fakestore.dtos.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreProductClient {

//    @Value("${fakestore.api.baseurl}")
//    private String fakestorebaseurl;

    private final String productPath = "/products";

//    private String productUrl = fakestorebaseurl+productPath+"/{id}";
    private String productUrl;
    private String productUrl2 = "https://fakestoreapi.com/products";
    private String createProductUrl = "https://fakestoreapi.com/products/";
    private String allProductUrl = "https://fakestoreapi.com/carts/";

    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder,@Value("${fakestore.api.baseurl}") String fakestorebaseurl){
        this.restTemplateBuilder = restTemplateBuilder;
        this.productUrl = fakestorebaseurl+productPath+"/{id}";
    }

    public FakeStoreProductDto getProductById(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =  restTemplate.getForEntity(
                productUrl,
                FakeStoreProductDto.class,
                id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product not found with id: " + id);

        }
        return fakeStoreProductDto;
    }
    public List<FakeStoreProductDto> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response =  restTemplate.getForEntity(
                allProductUrl,
                FakeStoreProductDto[].class);

        FakeStoreProductDto[] fakeStoreProductDto = response.getBody();

        return Arrays.asList(fakeStoreProductDto);
    }

    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =  restTemplate.postForEntity(createProductUrl,genericProductDto,FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        return fakeStoreProductDto;
    }

    public FakeStoreProductDto deleteProduct(Long id){

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                productUrl, HttpMethod.GET,null,FakeStoreProductDto.class,id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }
    public FakeStoreProductDto updateProduct(Long id, GenericProductDto genericProductDto){
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = productUrl2 + "/" + id;

        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(genericProductDto),
                FakeStoreProductDto.class
        );

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if (fakeStoreProductDto == null) {
            throw new RuntimeException("Update failed: no response body from server");
        }

        return fakeStoreProductDto;
    }
}
