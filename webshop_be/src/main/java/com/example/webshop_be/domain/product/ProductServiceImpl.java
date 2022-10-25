package com.example.webshop_be.domain.product;

import com.example.webshop_be.config.error.BadRequestException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private static final String NO_SUCH_ELEMENT_ERROR_MSG =
            "Entity with ID '%s' could not be found";

    private static final String SUCH_ELEMENT_ALREADY_EXISTS_ERROR_MSG =
            "Entity with ID '%s' already exists";

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product findById(String id) {
        Optional<Product> product = repository.findById(id);

        if (product.isPresent()) {
            return product.get();
        } else {
            throw new NoSuchElementException(String.format(NO_SUCH_ELEMENT_ERROR_MSG, id));
        }
    }

    @Override
    public Product createProduct(Product product) {
        if (repository.existsById(product.getId())) {
            throw new BadRequestException(
                    String.format(SUCH_ELEMENT_ALREADY_EXISTS_ERROR_MSG, product.getId()));
        } else {
            return repository.save(product);
        }
    }

    @Override
    public String updateProduct(String id, Product product) {
        repository.findById(id).map(product1 -> {
            product1.setBrand(product.getBrand());
            product1.setType(product.getType());
            product1.setPrice(product.getPrice());
            product1.setImageLink(product.getImageLink());
            product1.setDescription(product.getDescription());
            repository.save(product1);
            return "Payment got updated";
        }).orElseGet(() -> {
            product.setId(id);
            repository.save(product);
            return "Payment got inserted";
        });
        return "Payment is updated";
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
    public List<Product> getAllProducts() {
        if (repository.findAll().isEmpty()) {
            throw new NoSuchElementException(String.format("No User found in the database"));
        }
        return repository.findAll();
    }
}
