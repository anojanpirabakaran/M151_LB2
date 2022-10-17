package com.example.webshop_be.domain.authority;

import java.util.List;

public interface AuthorityService {
    Authority findByName(String name);

    void deleteByName(String name);

    void deleteById(String id);

    List<Authority> getAllAuthorities();

    Authority findById(String id);

    Authority createAuthority(Authority authority);

    String updateAuthority(String id, Authority authority);
}
