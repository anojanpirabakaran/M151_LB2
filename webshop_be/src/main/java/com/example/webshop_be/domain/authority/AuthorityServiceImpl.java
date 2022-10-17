package com.example.webshop_be.domain.authority;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    private static final String NO_SUCH_ELEMENT_ERROR_MSG =
            "Entity with ID '%s' could not be found";

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
        }
        authorityRepository.deleteRelationsToRolesById(id);

        authorityRepository.deleteById(id);
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
        return authorityRepository.save(authority);
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
