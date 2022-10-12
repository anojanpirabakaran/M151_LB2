package com.example.webshop_be.domain.shoe;

import com.example.webshop_be.config.generic.ExtendedDTO;
import com.example.webshop_be.domain.brand.Brand;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ShoeDTO extends ExtendedDTO {

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @NotNull
    @Size(min = 1, max = 500)
    private String description;

    @NotNull
    @Size(min = 1, max = 255)
    private String image;

    @NotNull
    private float price;

    @NotNull
    private Brand brand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
