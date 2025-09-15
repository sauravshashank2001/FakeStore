package com.example.demo.services;

import com.example.demo.dtos.GenericProductDto;

public interface ProductService {

    public GenericProductDto getProductById(Long id);
}
