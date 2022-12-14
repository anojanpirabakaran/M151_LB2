package com.example.webshop_be.domain.product;

import com.example.webshop_be.config.generic.ExtendedDTO;
import com.example.webshop_be.domain.brand.Brand;
import com.example.webshop_be.domain.type.Type;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDTO extends ExtendedDTO {

    @NotNull
    @Size(min = 1, max = 25)
    private String name;

    @NotNull
    @Size(min = 1, max = 4)
    private int price;

    @NotNull
    private Brand brand;

    @NotNull
    private Type type;

    @NotNull
    private String imageLink;

    @NotNull
    private String description;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
