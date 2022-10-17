package com.example.webshop_be.domain.role.mapper;

import com.example.webshop_be.domain.role.Role;
import com.example.webshop_be.domain.role.RoleDTO;
import java.util.Collection;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role fromDTO(RoleDTO roleDTO);

    List<Role> fromDTOs(List<RoleDTO> roleDTOS);

    RoleDTO toDTO(Role role);

    List<RoleDTO> toDTOs(List<Role> authorities);

    @Named("toWithoutAuthoritiesDTO")
    RoleDTO.WithoutAuthorities toWithoutAuthoritiesDTO(Role role);

    @IterableMapping(qualifiedByName = "toWithoutAuthoritiesDTO")
    Collection<RoleDTO.WithoutAuthorities> toWithoutAuthoritiesDTOs(Collection<Role> roles);
}
