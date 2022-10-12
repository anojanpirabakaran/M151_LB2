package com.example.webshop_be.domain.city;

import com.example.webshop_be.domain.city.mapper.CityMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityController {


    private final CityService cityService;
    private final CityMapper cityMapper;

    @Autowired
    public CityController(CityService cityService, CityMapper cityMapper) {
        this.cityService = cityService;
        this.cityMapper = cityMapper;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<CityDTO>> getAll() {
        List<City> cities = cityService.getAllCities();
        return new ResponseEntity<>(cityMapper.toDTOs(cities), HttpStatus.OK);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<CityDTO> getById(@PathVariable String id) {
        City city = cityService.findById(id);
        return new ResponseEntity<>(cityMapper.toDTO(city), HttpStatus.OK);
    }

    //TODO: Create, Update, Delete
}
