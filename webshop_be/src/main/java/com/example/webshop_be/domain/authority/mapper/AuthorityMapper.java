package com.example.webshop_be.domain.authority.mapper;

import com.example.webshop_be.domain.authority.Authority;
import com.example.webshop_be.domain.authority.AuthorityDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {
    Authority fromDTO(AuthorityDTO authorityDTO);

    List<Authority> fromDTOs(List<AuthorityDTO> authorityDTOS);

    AuthorityDTO toDTO(Authority authority);

    List<AuthorityDTO> toDTOs(List<Authority> authorities);
}
