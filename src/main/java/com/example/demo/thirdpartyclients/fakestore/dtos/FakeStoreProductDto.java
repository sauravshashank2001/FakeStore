package com.example.demo.thirdpartyclients.fakestore.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {

    public long id;
    public String title;
    public String description;
    public String image;
    public String category;
    public double price;

}
