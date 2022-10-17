package com.example.webshop_be.domain.authority;

import com.example.webshop_be.config.generic.ExtendedEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "authority")
public class Authority extends ExtendedEntity {

    @Column(name = "name", nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
