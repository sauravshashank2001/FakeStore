package com.example.demo.services;

import com.example.demo.dtos.GenericProductDto;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.thirdpartyclients.fakestore.FakeStoreProductClient;
import com.example.demo.thirdpartyclients.fakestore.dtos.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private FakeStoreProductClient fakeStoreProductClient;

    @Autowired
    public FakeStoreProductService(FakeStoreProductClient fakeStoreProductClient){
        this.fakeStoreProductClient = fakeStoreProductClient;
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

    public GenericProductDto getProductById(Long id){
        return convertFakeStoreDtoToGenericProducts(fakeStoreProductClient.getProductById(id));
    }

    public List<GenericProductDto> getAllProducts(){
        List<FakeStoreProductDto> fakeStoreProductDto = fakeStoreProductClient.getAllProducts();
        List<GenericProductDto> genericProducts = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProduct : fakeStoreProductDto) {
            GenericProductDto genericProduct = convertFakeStoreDtoToGenericProducts(fakeStoreProduct);
            genericProducts.add(genericProduct);
        }

        return genericProducts;
    }

    public GenericProductDto createProduct(GenericProductDto genericProductDto){
       return convertFakeStoreDtoToGenericProducts(fakeStoreProductClient.createProduct(genericProductDto));
    }

    public GenericProductDto deleteProduct(Long id){


        return convertFakeStoreDtoToGenericProducts(fakeStoreProductClient.deleteProduct(id));
    }
    public GenericProductDto updateProduct(Long id, GenericProductDto genericProductDto){

        return convertFakeStoreDtoToGenericProducts(fakeStoreProductClient.updateProduct(id,genericProductDto));
    }
}
