package com.example.webshop_be.domain.brand;

import com.example.webshop_be.config.generic.ExtendedEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "brand")
public class Brand extends ExtendedEntity {

    @Column(name = "name", nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
