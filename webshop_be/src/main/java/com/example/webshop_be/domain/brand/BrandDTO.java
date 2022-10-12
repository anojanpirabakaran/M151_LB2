package com.example.webshop_be.domain.brand;

import com.example.webshop_be.config.generic.ExtendedDTO;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BrandDTO extends ExtendedDTO {

    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
