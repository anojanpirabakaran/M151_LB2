package com.example.webshop_be.domain.city;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, String> {

    boolean existsByName(String name);

    boolean existsByPostalCode(int postalCode);
}
