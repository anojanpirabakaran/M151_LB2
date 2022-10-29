package com.example.webshop_be.domain.type;

import com.example.webshop_be.config.generic.ExtendedDTO;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TypeDTO extends ExtendedDTO {
    @NotNull
    @Size(min = 1, max = 25)
    private String name;

    public String getName() {
        return name;
    }

    public TypeDTO setName(String name) {
        this.name = name;
        return this;
    }
}
