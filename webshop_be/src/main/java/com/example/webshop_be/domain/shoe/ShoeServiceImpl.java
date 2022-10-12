package com.example.webshop_be.domain.shoe;

import com.example.webshop_be.config.error.BadRequestException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoeServiceImpl implements ShoeService{

    private final ShoeRepository repository;

    private static final String NO_SUCH_ELEMENT_ERROR_MSG =
            "Entity with ID '%s' could not be found";

    @Autowired
    public ShoeServiceImpl(ShoeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Shoe findById(String id) {
        Optional<Shoe> shoe = repository.findById(id);

        if (shoe.isPresent()) {
            return shoe.get();
        } else {
            throw new NoSuchElementException(String.format(NO_SUCH_ELEMENT_ERROR_MSG, id));
        }
    }

    @Override
    public Shoe createCity(Shoe shoe) {
        return repository.save(shoe);
    }

    @Override
    public String updateCity(String id, Shoe shoe) {
        if (repository.existsById(id)) {
            checkUpdatedEntityId(id, shoe);

            shoe.setId(id);
            repository.save(shoe);
            return "Shoe saved";
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
    public List<Shoe> getAllShoes() {
        return repository.findAll();
    }

    protected void checkUpdatedEntityId(String id, Shoe shoe) {
        if (shoe.getId() != null) {
            if (id.equals(shoe.getId())) {
                return;
            }
            throw new BadRequestException(
                    String.format("Path variable ID '%s' and Request body ID '%s' are not equal",
                            id, shoe.getId()));
        }
    }
}
