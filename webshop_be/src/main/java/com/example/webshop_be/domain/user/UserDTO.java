package com.example.webshop_be.domain.user;

import com.example.webshop_be.config.generic.ExtendedDTO;
import com.example.webshop_be.domain.role.RoleDTO;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO extends ExtendedDTO {
    @Email
    @NotNull
    protected String email;
    @Size(min = 1, max = 50)
    @NotNull
    protected String firstName;
    @NotNull
    @Size(min = 1, max = 50)
    protected String lastName;

    @NotNull
    protected String password;

    @NotNull
    protected Set<RoleDTO> roles;


    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
