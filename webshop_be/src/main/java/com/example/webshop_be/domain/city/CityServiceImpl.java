package com.example.webshop_be.domain.city;

import com.example.webshop_be.config.error.BadRequestException;
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

    @Autowired
    public CityServiceImpl(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public City createCity(City entity) {
        return repository.save(entity);
    }

    @Override
    public String updateCity(String id, City entity) {
        if (repository.existsById(id)) {
            checkUpdatedEntityId(id, entity);

            entity.setId(id);
            repository.save(entity);
            return "City saved";
        } else {
            throw new NoSuchElementException(String.format(NO_SUCH_ELEMENT_ERROR_MSG, id));
        }
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

    protected void checkUpdatedEntityId(String id, City city) {
        if (city.getId() != null) {
            if (id.equals(city.getId())) {
                return;
            }
            throw new BadRequestException(
                    String.format("Path variable ID '%s' and Request body ID '%s' are not equal",
                            id, city.getId()));
        }
    }
}
