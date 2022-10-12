package com.example.webshop_be.domain.brand.mapper;

import com.example.webshop_be.domain.brand.Brand;
import com.example.webshop_be.domain.brand.BrandDTO;
import java.util.List;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BrandMapper {
    Brand fromDTO(BrandDTO brandDTO);

    List<Brand> fromDTOs(List<BrandDTO> brandDTOS);

    BrandDTO toDTO(Brand brand);

    List<BrandDTO> toDTOs(List<Brand> brand);
}
