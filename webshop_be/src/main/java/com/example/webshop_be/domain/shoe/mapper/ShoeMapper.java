package com.example.webshop_be.domain.shoe.mapper;

import com.example.webshop_be.domain.shoe.Shoe;
import com.example.webshop_be.domain.shoe.ShoeDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShoeMapper {
    Shoe fromDTO(ShoeDTO shoeDTO);

    List<Shoe> fromDTOs(List<ShoeDTO> shoeDTOs);

    ShoeDTO toDTO(Shoe shoe);

    List<ShoeDTO> toDTOs(List<Shoe> shoes);
}
