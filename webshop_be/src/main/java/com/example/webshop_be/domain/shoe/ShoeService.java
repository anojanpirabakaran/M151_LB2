package com.example.webshop_be.domain.shoe;

import java.util.List;

public interface ShoeService {
    Shoe findById(String id);

    Shoe createCity(Shoe shoe);

    String updateCity(String id, Shoe shoe);

    void deleteById(String id);

    List<Shoe> getAllShoes();

}
