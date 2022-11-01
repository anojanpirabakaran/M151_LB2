package com.example.webshop_be.domain.brand;

import java.util.List;

public interface BrandService {
    Brand findById(String id);

    Brand createBrand(Brand brand);

    String updateBrand(String id, Brand brand) throws Exception;

    String deleteById(String id);

    List<Brand> getAllBrands();
}
