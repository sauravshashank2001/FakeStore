package com.example.demo.Controller;


import com.example.demo.dtos.ExceptionDTO;
import com.example.demo.dtos.GenericProductDto;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericProductDto> deleteProduct(@PathVariable("id") Long id){
//        return productService.deleteProduct(id);
        return new ResponseEntity<>(productService.deleteProduct(id),HttpStatus.OK);


    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericProductDto> updateProduct(@PathVariable("id") Long id,@RequestBody GenericProductDto genericProductDto){
        return new ResponseEntity<>(productService.updateProduct(id,genericProductDto),HttpStatus.OK);
    }

//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ExceptionDTO> handleNotFoundException(NotFoundException notFoundException){
//        ExceptionDTO dto = new ExceptionDTO(HttpStatus.NOT_FOUND, notFoundException.getMessage());
//        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
//    }
}
