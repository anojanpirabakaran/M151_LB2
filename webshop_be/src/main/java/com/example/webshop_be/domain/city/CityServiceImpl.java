package com.example.webshop_be.domain.city;

import com.example.webshop_be.config.error.BadRequestException;
import com.example.webshop_be.domain.brand.Brand;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository repository;
    private static final String NO_SUCH_ELEMENT_ERROR_MSG =
            "Entity with ID '%s' could not be found";

    private static final String SUCH_ELEMENT_ALREADY_EXISTS_ERROR_MSG =
            "Entity with ID '%s' already exists";

    @Autowired
    public CityServiceImpl(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public City createCity(City entity) {
        if (repository.existsById(entity.getId())) {
            throw new BadRequestException(
                    String.format(SUCH_ELEMENT_ALREADY_EXISTS_ERROR_MSG, entity.getId()));
        } else {
            return repository.save(entity);
        }
    }

    @Override
    public String updateCity(String id, City city) {
        repository.findById(id)
                .map(city1 -> {
                    city1.setName(city.getName());
                    city1.setPostalCode(city.getPostalCode());
                    repository.save(city1);
                    return "City got updated";
                }).orElseGet(() -> {
                    city.setId(id);
                    repository.save(city);
                    return "Brand got inserted";
                });
        return "Brand is updated";
    }

    @Override
    public void deleteById(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NoSuchElementException(String.format(NO_SUCH_ELEMENT_ERROR_MSG, id));
        }
    }

    @Override
    public List<City> getAllCities() {
        return repository.findAll();
    }

    @Override
    public City findById(String id) {
        Optional<City> city = repository.findById(id);

        if (city.isPresent()) {
            return city.get();
        } else {
            throw new NoSuchElementException(String.format(NO_SUCH_ELEMENT_ERROR_MSG, id));
        }
    }
}
