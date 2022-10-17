package com.example.webshop_be.domain.role;

import java.util.List;
import java.util.NoSuchElementException;

public interface RoleService{

    Role findByName(String name);

    void deleteByName(String name);

    Role addAuthorityToRole(String roleId, String authorityId) throws NoSuchElementException;

    Role removeAuthorityFromRole(String roleId, String authorityId) throws NoSuchElementException;

    void deleteById(String id);

    List<Role> getAllRoles();

    Role findById(String id);

    Role createRole(Role role);

    String updateRole(String id, Role role);
}
