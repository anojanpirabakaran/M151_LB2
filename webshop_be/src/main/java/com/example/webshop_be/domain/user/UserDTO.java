package com.example.webshop_be.domain.user;

import com.example.webshop_be.config.generic.ExtendedDTO;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO extends ExtendedDTO {
    @Email
    @NotNull
    protected String email;
    @Size(min = 1, max = 255)
    @NotNull
    protected String firstName;
    @NotNull
    protected String lastName;

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
}
