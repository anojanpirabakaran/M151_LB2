package com.example.webshop_be.domain.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, String> {

    Type findByName(String name);

    boolean existsByName(String name);
}
