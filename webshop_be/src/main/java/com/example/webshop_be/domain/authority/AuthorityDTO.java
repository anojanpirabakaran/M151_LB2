package com.example.webshop_be.domain.authority;

import com.example.webshop_be.config.generic.ExtendedDTO;
import javax.validation.constraints.NotNull;

public class AuthorityDTO extends ExtendedDTO {

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
