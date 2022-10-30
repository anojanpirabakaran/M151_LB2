package com.example.webshop_be.domain.user;

import com.example.webshop_be.config.error.BadRequestException;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String NOTFOUND = "User with ID '%s' not found";

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            logger.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            logger.info("User found in the database: {}", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), authorities);
    }

    @Override
    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NoSuchElementException(String.format(NOTFOUND, id));
        }
    }

    @Override
    public User createUser(User user) {
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        if (userRepository.existsById(user.getId())) {
            throw new BadRequestException(
                    String.format("User with Email '%s' already exists", user.getEmail()));
        } else {
            user.setPassword(encodedPassword);
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
        if (userRepository.findAll().isEmpty()) {
            throw new NoSuchElementException(String.format("No User found in the database"));
        }
        return userRepository.findAll();
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
