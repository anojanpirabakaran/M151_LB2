package com.example.webshop_be.domain.type.mapper;


import com.example.webshop_be.domain.type.Type;
import com.example.webshop_be.domain.type.TypeDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeMapper {
    Type fromDTO(TypeDTO typeDTO);

    List<Type> fromDTOs(List<TypeDTO> typeDTOS);

    TypeDTO toDTO(Type type);

    List<TypeDTO> toDTOs(List<Type> types);
}
