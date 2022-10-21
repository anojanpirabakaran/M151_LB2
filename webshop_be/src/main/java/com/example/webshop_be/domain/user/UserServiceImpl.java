package com.example.webshop_be.domain.user;

import com.example.webshop_be.config.error.BadRequestException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final String NOTFOUND = "User with ID '%s' not found";

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(String id) {
        if (userRepository.existsById(id)) {
            Optional<User> user = userRepository.findById(id);
            return user.orElse(null);
        } else {
            throw new NoSuchElementException(String.format("User with ID '%s' not found", id));
        }
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()) != null) {
            throw new BadRequestException(
                    String.format("User with Email '%s' already exists", user.getEmail()));
        } else {
            return userRepository.save(user);
        }
    }

    @Override
    public String updateUser(String id, User user) {
        userRepository.findById(id)
                .map(user1 -> {
                    user1.setFirstName(user.getFirstName());
                    user1.setEmail(user.getEmail());
                    user1.setLastName(user.getLastName());
                    user1.setAddress(user.getAddress());
                    user1.setPassword(user.getPassword());
                    user1.setEmail(user.getEmail());
                    user1.setCity(user.getCity());
                    userRepository.save(user1);
                    return "User got updated";
                }).orElseGet(() -> {
                    user.setId(id);
                    userRepository.save(user);
                    return "User got inserted";
                });
        return "User is updated";
    }

    @Override
    public String deleteById(String id) {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException(String.format(NOTFOUND, id));
        }
        userRepository.deleteById(id);
        return "User deleted";
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
