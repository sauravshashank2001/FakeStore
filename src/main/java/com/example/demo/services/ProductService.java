package com.example.demo.services;

import com.example.demo.dtos.GenericProductDto;

import java.util.ArrayList;

public interface ProductService {

    public GenericProductDto getProductById(Long id);
    public GenericProductDto deleteProduct(Long id);
    public GenericProductDto createProduct(GenericProductDto genericProductDto );
    public ArrayList<GenericProductDto> getAllProducts();
}
