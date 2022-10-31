package com.example.webshop_be.domain.user;

import com.example.webshop_be.domain.role.Role;
import java.util.List;

public interface UserService {

    User findById(String id);

    User createUser(User user);

    String updateUser(String id, User user);

    String deleteById(String id);

    List<User> getAllUsers();

    User getByEmail(String email);

    void addRoleToUser(String username, String rolename);
}
