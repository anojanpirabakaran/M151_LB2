package com.example.webshop_be.domain.shoe;

import com.example.webshop_be.config.generic.ExtendedEntity;
import com.example.webshop_be.domain.brand.Brand;
import javax.persistence.*;

@Entity
@Table(name = "shoe")
public class Shoe extends ExtendedEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(length = 400, name = "image_Link")
    private String image;

    @Column(name = "price")
    private float price;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brands_id", referencedColumnName = "id")
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
