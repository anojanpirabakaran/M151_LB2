package com.example.webshop_be.domain.city.mapper;

import com.example.webshop_be.domain.city.City;
import com.example.webshop_be.domain.city.CityDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper{
    City fromDTO(CityDTO cityDTO);

    List<City> fromDTOs(List<CityDTO> cityDTOList);

    CityDTO toDTO(City city);

    List<CityDTO> toDTOs(List<City> cityList);
}
