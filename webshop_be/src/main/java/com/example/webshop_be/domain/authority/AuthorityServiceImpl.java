package com.example.webshop_be.domain.authority;

import com.example.webshop_be.config.error.BadRequestException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private static final String NO_SUCH_ELEMENT_ERROR_MSG =
            "Entity with ID '%s' could not be found";

    private static final String SUCH_ELEMENT_ALREADY_EXISTS_ERROR_MSG =
            "Entity with ID '%s' already exists";
    private final AuthorityRepository authorityRepository;


    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Authority findByName(String name) {
        return authorityRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Authority with name '%s' not found", name)));
    }

    @Override
    public void deleteByName(String name) {
        authorityRepository.deleteByName(name);
    }

    @Override
    public void deleteById(String id) throws NoSuchElementException {
        if (!authorityRepository.existsById(id)) {
            throw new NoSuchElementException(String.format("Authority with ID '%s' not found", id));
        } else {
            authorityRepository.deleteRelationsToRolesById(id);
            authorityRepository.deleteById(id);
        }
    }

    @Override
    public List<Authority> getAllAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority findById(String id) {
        Optional<Authority> authority = authorityRepository.findById(id);

        if (authority.isPresent()) {
            return authority.get();
        } else {
            throw new NoSuchElementException(String.format(NO_SUCH_ELEMENT_ERROR_MSG, id));
        }
    }

    @Override
    public Authority createAuthority(Authority authority) {
        if (authorityRepository.existsById(authority.getId())) {
            throw new BadRequestException(
                    String.format(SUCH_ELEMENT_ALREADY_EXISTS_ERROR_MSG, authority.getId()));
        } else {
            return authorityRepository.save(authority);
        }
    }

    @Override
    public String updateAuthority(String id, Authority authority) {
        authorityRepository.findById(id)
                .map(authority1 -> {
                    authority1.setName(authority.getName());
                    authorityRepository.save(authority1);
                    return "Authority got updated";
                }).orElseGet(() -> {
                    authority.setId(id);
                    authorityRepository.save(authority);
                    return "Authority got inserted";
                });
        return "Authority is updated";
    }
}
