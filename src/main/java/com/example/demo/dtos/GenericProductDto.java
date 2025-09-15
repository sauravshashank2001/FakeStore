package com.example.demo.dtos;

import com.example.demo.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {

    public long id;
    public String title;
    public String description;
    public String image;
    public String category;
    public double price;

}
