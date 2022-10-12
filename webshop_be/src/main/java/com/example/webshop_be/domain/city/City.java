package com.example.webshop_be.domain.city;

import com.example.webshop_be.config.generic.ExtendedEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City extends ExtendedEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "postal_code", nullable = false)
    private int postalCode;

    public String getName() {
        return name;
    }

    public City setName(String name) {
        this.name = name;
        return this;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public City setPostalCode(int postalCode) {
        this.postalCode = postalCode;
        return this;
    }
}
