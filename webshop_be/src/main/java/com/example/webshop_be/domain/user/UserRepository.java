package com.example.webshop_be.domain.user;

import com.example.webshop_be.domain.role.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User getUserByEmail(String email);

    boolean existsByEmail(String email);

    User findByEmail(String email);
}
