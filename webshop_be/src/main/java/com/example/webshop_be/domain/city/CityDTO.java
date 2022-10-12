package com.example.webshop_be.domain.city;

import com.example.webshop_be.config.generic.ExtendedDTO;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CityDTO extends ExtendedDTO {

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @NotNull
    private int postalCode;

    public String getName() {
        return name;
    }

    public CityDTO setName(String name) {
        this.name = name;
        return this;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public CityDTO setPostalCode(int postalCode) {
        this.postalCode = postalCode;
        return this;
    }
}
