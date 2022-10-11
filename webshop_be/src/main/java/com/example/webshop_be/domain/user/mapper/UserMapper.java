package com.example.webshop_be.domain.user.mapper;

import com.example.webshop_be.domain.user.User;
import com.example.webshop_be.domain.user.UserDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserMapper {
    User fromDTO(UserDTO userDTO);

    List<User> fromDTOs(List<UserDTO> userDTOS);

    UserDTO toDTO(User user);

    List<UserDTO> toDTOs(List<User> users);
}
