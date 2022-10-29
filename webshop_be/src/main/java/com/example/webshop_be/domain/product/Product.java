package com.example.webshop_be.domain.product;

import com.example.webshop_be.config.generic.ExtendedEntity;
import com.example.webshop_be.domain.brand.Brand;
import com.example.webshop_be.domain.type.Type;
import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product extends ExtendedEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private Type type;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
