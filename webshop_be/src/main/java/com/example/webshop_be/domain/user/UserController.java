package com.example.webshop_be.domain.user;

import com.example.webshop_be.domain.role.Role;
import com.example.webshop_be.domain.user.mapper.UserMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<UserDTO> getById(@PathVariable String id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.OK);
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<UserDTO>> getAll() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(userMapper.toDTOs(users), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}/", "/{id}"})
    public ResponseEntity<Void> delete(@PathVariable String id) throws Exception {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO)
            throws Exception {
        User user = userService.createUser(userMapper.fromDTO(userDTO));
        return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.CREATED);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public ResponseEntity<UserDTO> updateById(@PathVariable String id,
                                              @RequestBody UserDTO userDTO)
            throws Exception {
        User user = userMapper.fromDTO(userDTO);
        userService.updateUser(id, user);
        return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.OK);
    }
}
