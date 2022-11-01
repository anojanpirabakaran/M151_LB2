package com.example.webshop_be.domain.product;

import java.util.List;

public interface ProductService {

    Product findById(String id);

    Product createProduct(Product product);

    String updateProduct(String id, Product product) throws Exception;

    void deleteById(String id);

    List<Product> getAllProducts();
}
