package com.example.webshop_be.domain.type;

import java.util.List;

public interface TypeService {

    void deleteById(String id);

    List<Type> getAllTypes();

    Type findById(String id);

    Type createType(Type type);

    String updateType(String id, Type type) throws Exception;
}
