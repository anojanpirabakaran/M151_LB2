package com.example.webshop_be.domain.role;

import com.example.webshop_be.config.generic.ExtendedDTO;
import com.example.webshop_be.domain.authority.AuthorityDTO;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoleDTO extends ExtendedDTO {

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    private Set<AuthorityDTO> authorities;

    public String getName() {
        return name;
    }

    public RoleDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Set<AuthorityDTO> getAuthorities() {
        return authorities;
    }

    public RoleDTO setAuthorities(Set<AuthorityDTO> authorities) {
        this.authorities = authorities;
        return this;
    }

    public static class WithoutAuthorities extends ExtendedDTO {

        @NotNull
        @Size(min = 1, max = 255)
        private String name;

        public String getName() {
            return name;
        }

        public WithoutAuthorities setName(String name) {
            this.name = name;
            return this;
        }

    }
}
