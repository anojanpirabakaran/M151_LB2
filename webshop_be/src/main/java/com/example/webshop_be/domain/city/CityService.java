package com.example.webshop_be.domain.city;

import java.util.List;

public interface CityService {

    City findById(String id);

    City createCity(City city);

    String updateCity(String id, City city);

    void deleteById(String id);

    List<City> getAllCities();
}
