package com.example.webshop_be.domain.brand;

import com.example.webshop_be.config.error.BadRequestException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {

    private final String NOTFOUND = "Brand with ID '%s' not found";

    private final String SUCH_ELEMENT_ALREADY_EXISTS_ERROR_MSG =
            "Entity with Name '%s' already exists";

    @Autowired
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand findById(String id) {
        if (brandRepository.existsById(id)) {
            Optional<Brand> brand = brandRepository.findById(id);
            log.info("Brand found in the database: {}", brand.get());
            return brand.get();
        } else {
            log.error("Brand not found in the database");
            throw new BadRequestException(String.format("Brand with ID '%s' not found", id));
        }
    }

    @Override
    public Brand createBrand(Brand brand) {
        if (brandRepository.existsByName(brand.getName())) {
            throw new BadRequestException(
                    String.format(SUCH_ELEMENT_ALREADY_EXISTS_ERROR_MSG, brand.getName()));
        } else {
            return brandRepository.save(brand);
        }
    }

    @Override
    public String updateBrand(String id, Brand brand) throws Exception {
        if (brandRepository.existsById(id)) {
            brandRepository.findById(id)
                    .map(brand1 -> {
                        brand1.setName(brand.getName());
                        if (brandRepository.existsByName(brand1.getName())) {
                            throw new BadRequestException("Brand already exists");
                        } else {
                            brandRepository.save(brand1);
                        }
                        return "Brand updating";
                    }).orElseThrow(() -> new Exception("Brand not found - " + brand));
            return "Brand is updated";
        } else {
            throw new BadRequestException("Brand id doesnt exists");
        }

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

