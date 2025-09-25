package com.example.demo.services;

import com.example.demo.dtos.ExceptionDTO;
import com.example.demo.dtos.FakeStoreProductDto;
import com.example.demo.dtos.GenericProductDto;
import com.example.demo.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;


@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private String productUrl = "https://fakestoreapi.com/products/{id}";
    private String productUrl2 = "https://fakestoreapi.com/products";
    private String createProductUrl = "https://fakestoreapi.com/products/";
    private String allProductUrl = "https://fakestoreapi.com/carts/";

    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public GenericProductDto convertFakeStoreDtoToGenericProducts(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        return genericProductDto;
    }

    @Override
    public GenericProductDto getProductById(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =  restTemplate.getForEntity(
                productUrl,
                FakeStoreProductDto.class,
                id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product not found with id: " + id);

        }
//        GenericProductDto genericProductDto = new GenericProductDto();
//        genericProductDto.setId(fakeStoreProductDto.getId());
//        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
//        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
//        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
//        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
//        genericProductDto.setImage(fakeStoreProductDto.getImage());
        GenericProductDto genericProductDto = convertFakeStoreDtoToGenericProducts(fakeStoreProductDto);
        return genericProductDto;
    }
    @Override
    public ArrayList<GenericProductDto> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response =  restTemplate.getForEntity(
                allProductUrl,
                FakeStoreProductDto[].class);

        FakeStoreProductDto[] fakeStoreProductDto = response.getBody();
        if (fakeStoreProductDto == null || fakeStoreProductDto.length == 0) {
            throw new RuntimeException("No products found");
        }
        ArrayList<GenericProductDto> genericProducts = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProduct : fakeStoreProductDto) {
//            GenericProductDto genericProduct = new GenericProductDto();
//            genericProduct.setId(fakeStoreProduct.getId());
//            genericProduct.setTitle(fakeStoreProduct.getTitle());
//            genericProduct.setPrice(fakeStoreProduct.getPrice());
//            genericProduct.setDescription(fakeStoreProduct.getDescription());
//            genericProduct.setCategory(fakeStoreProduct.getCategory());
//            genericProduct.setImage(fakeStoreProduct.getImage());
            GenericProductDto genericProduct = convertFakeStoreDtoToGenericProducts(fakeStoreProduct);
            genericProducts.add(genericProduct);
        }

        return genericProducts;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto){
       RestTemplate restTemplate = restTemplateBuilder.build();
       ResponseEntity<FakeStoreProductDto> response =  restTemplate.postForEntity(createProductUrl,genericProductDto,FakeStoreProductDto.class);

       FakeStoreProductDto fakeStoreProductDto = response.getBody();
//        GenericProductDto genericProductDto1 = new GenericProductDto();
//        genericProductDto1.setId(fakeStoreProductDto.getId());
//        genericProductDto1.setTitle(fakeStoreProductDto.getTitle());
//        genericProductDto1.setPrice(fakeStoreProductDto.getPrice());
//        genericProductDto1.setDescription(fakeStoreProductDto.getDescription());
//        genericProductDto1.setCategory(fakeStoreProductDto.getCategory());
//        genericProductDto1.setImage(fakeStoreProductDto.getImage());
        GenericProductDto genericProductDto1 = convertFakeStoreDtoToGenericProducts(fakeStoreProductDto);

        return genericProductDto1;
    }

    @Override
    public GenericProductDto deleteProduct(Long id){

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                productUrl, HttpMethod.GET,null,FakeStoreProductDto.class,id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
//        GenericProductDto genericProductDto = new GenericProductDto();
//        genericProductDto.setId(fakeStoreProductDto.getId());
//        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
//        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
//        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
//        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
//        genericProductDto.setImage(fakeStoreProductDto.getImage());
        GenericProductDto genericProductDto = convertFakeStoreDtoToGenericProducts(fakeStoreProductDto);
        return genericProductDto;
    }
    @Override
    public GenericProductDto updateProduct(Long id, GenericProductDto genericProductDto){
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

        return convertFakeStoreDtoToGenericProducts(fakeStoreProductDto);
    }
}
