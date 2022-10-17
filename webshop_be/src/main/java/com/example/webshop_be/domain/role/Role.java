package com.example.webshop_be.domain.role;

import com.example.webshop_be.config.generic.ExtendedEntity;
import com.example.webshop_be.domain.authority.Authority;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "role")
public class Role extends ExtendedEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_authority", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Authority> authorities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
