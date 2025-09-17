package com.example.demo.services;

import com.example.demo.dtos.GenericProductDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("selfProductServiceImpl")
public class SelfProductServiceImplementation implements ProductService{

    @Override
    public GenericProductDto getProductById(Long id){
        System.out.print("Calling from self product service");
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto){
        return null;
    }
    @Override
    public ArrayList<GenericProductDto> getAllProducts(){
        return null;
    }
    @Override
    public GenericProductDto deleteProduct(Long id){
        return null;
    }

}
