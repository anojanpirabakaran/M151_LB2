package com.example.webshop_be.domain.role;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private static final String NO_SUCH_ELEMENT_ERROR_MSG =
            "Entity with ID '%s' could not be found";

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Role with name '%s' could not be found", name)));
    }

    @Override
    public void deleteByName(String name) {
        roleRepository.deleteByName(name);
    }

    @Override
    public void deleteById(String id) {
        if (!roleRepository.existsById(id)) {
            throw new NoSuchElementException(String.format("Role with ID '%s' not found", id));
        } else {
            roleRepository.deleteRelationsToUsersById(id);
            roleRepository.deleteById(id);
        }
    }

    @Override
    public List<Role> getAllRoles() {
        if (roleRepository.findAll().isEmpty()) {
            throw new NoSuchElementException(String.format("No Role found in the database"));
        }
        return roleRepository.findAll();
    }

    @Override
    public Role findById(String id) {
        Optional<Role> role = roleRepository.findById(id);

        if (role.isPresent()) {
            return role.get();
        } else {
            throw new NoSuchElementException(String.format(NO_SUCH_ELEMENT_ERROR_MSG, id));
        }
    }

    @Override
    public Role createRole(Role role) {
        if (roleRepository.existsById(role.getId())) {
            return roleRepository.save(role);
        } else {
            throw new NoSuchElementException(
                    String.format(NO_SUCH_ELEMENT_ERROR_MSG, role.getId()));
        }
    }

    @Override
    public String updateRole(String id, Role role) {
        roleRepository.findById(id)
                .map(role1 -> {
                    role1.setName(role.getName());
                    roleRepository.save(role1);
                    return "Role got updated";
                }).orElseGet(() -> {
                    role.setId(id);
                    roleRepository.save(role);
                    return "Role got inserted";
                });
        return "Role is updated";
    }
}
