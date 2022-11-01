package com.example.webshop_be.domain.type;

import com.example.webshop_be.config.error.BadRequestException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService {

    private final String NO_SUCH_ELEMENT_ERROR_MSG =
            "Entity with ID '%s' could not be found";

    private final TypeRepository typeRepository;

    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public void deleteById(String id) {
        if (!typeRepository.existsById(id)) {
            throw new NoSuchElementException(String.format("Type with ID '%s' not found", id));
        } else {
            typeRepository.deleteById(id);
        }
    }

    @Override
    public List<Type> getAllTypes() {
        if (typeRepository.findAll().isEmpty()) {
            throw new NoSuchElementException(String.format("No Type found in the database"));
        }
        return typeRepository.findAll();
    }

    @Override
    public Type findById(String id) {
        Optional<Type> type = typeRepository.findById(id);

        if (type.isPresent()) {
            return type.get();
        } else {
            throw new NoSuchElementException(String.format(NO_SUCH_ELEMENT_ERROR_MSG, id));
        }
    }

    @Override
    public Type createType(Type type) {
        if (typeRepository.existsByName(type.getName())) {
            return typeRepository.save(type);
        } else {
            throw new NoSuchElementException(
                    String.format(NO_SUCH_ELEMENT_ERROR_MSG, type.getName()));
        }
    }

    @Override
    public String updateType(String id, Type type) throws Exception {
        if (typeRepository.existsById(id) && !typeRepository.existsByName(type.getName())) {
            typeRepository.findById(id)
                    .map(type1 -> {
                        type1.setName(type.getName());
                        typeRepository.save(type1);
                        return "Type updating";
                    }).orElseThrow(() -> new Exception("Type not found - " + type));
            return "Type is updated";
        } else {
            throw new BadRequestException(
                    String.format("Type id doesnt exists or Type name already exists"));
        }

    }
}
