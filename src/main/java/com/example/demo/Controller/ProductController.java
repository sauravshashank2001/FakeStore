package com.example.demo.Controller;


import com.example.demo.dtos.GenericProductDto;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {

    public ProductService productService;

    @Autowired
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/")
    public ArrayList<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }



    public void updateProductById(){

    }

    @PostMapping("/")
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
        return productService.createProduct(genericProductDto);
    }
}
