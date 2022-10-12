package com.example.webshop_be.domain.brand;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

    private static final String NOTFOUND = "Brand with ID '%s' not found";

    @Autowired
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand findById(String id) {
        if (brandRepository.existsById(id)) {
            Optional<Brand> brand = brandRepository.findById(id);
            return brand.orElse(null);
        } else {
            throw new NoSuchElementException(String.format("Brand with ID '%s' not found", id));
        }
    }

    @Override
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public String updateBrand(String id, Brand brand) {
        brandRepository.findById(id)
                .map(brand1 -> {
                    brand1.setName(brand.getName());
                    brandRepository.save(brand1);
                    return "Brand got updated";
                }).orElseGet(() -> {
                    brand.setId(id);
                    brandRepository.save(brand);
                    return "Brand got inserted";
                });
        return "Brand is updated";
    }

    @Override
    public String deleteById(String id) {
        if (!brandRepository.existsById(id)) {
            throw new NoSuchElementException(String.format(NOTFOUND, id));
        }

        brandRepository.deleteById(id);
        return "Brand deleted";
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
}

