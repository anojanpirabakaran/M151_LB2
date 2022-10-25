package com.example.webshop_be.domain.product;

import com.example.webshop_be.config.generic.ExtendedEntity;
import com.example.webshop_be.domain.brand.Brand;
import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product extends ExtendedEntity {

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    @Column(name = "type")
    private TypeEnum type;

    @Column(name = "image_link")
    private String imageLink;

    @Column(name = "description")
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

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
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
