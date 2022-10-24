package com.example.webshop_be.domain.brand;

import com.example.webshop_be.config.error.BadRequestException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

    private static final String NOTFOUND = "Brand with ID '%s' not found";

    private static final String SUCH_ELEMENT_ALREADY_EXISTS_ERROR_MSG =
            "Entity with ID '%s' already exists";

    @Autowired
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand findById(String id) {
        if (brandRepository.existsById(id)) {
            Optional<Brand> brand = brandRepository.findById(id);
            return brand.get();
        } else {
            throw new BadRequestException(String.format("Brand with ID '%s' not found", id));
        }
    }

    @Override
    public Brand createBrand(Brand brand) {
        if (brandRepository.existsById(brand.getId())) {
            throw new BadRequestException(
                    String.format(SUCH_ELEMENT_ALREADY_EXISTS_ERROR_MSG, brand.getId()));
        } else {
            return brandRepository.save(brand);
        }
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
        if (brandRepository.findAll().isEmpty()) {
            throw new NoSuchElementException(String.format("No User found in the database"));
        }
        return brandRepository.findAll();
    }
}

