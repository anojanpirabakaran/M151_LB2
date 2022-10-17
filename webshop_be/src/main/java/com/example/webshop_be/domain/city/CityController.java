package com.example.webshop_be.domain.city;

import com.example.webshop_be.domain.city.mapper.CityMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping({"/", ""})
    public ResponseEntity<CityDTO> create(@RequestBody CityDTO cityDTO)
            throws Exception {
        City city =
                cityService.createCity(cityMapper.fromDTO(cityDTO));
        return new ResponseEntity<>(cityMapper.toDTO(city), HttpStatus.CREATED);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public ResponseEntity<CityDTO> updateById(@PathVariable String id,
                                              @RequestBody CityDTO cityDTO)
            throws Exception {
        City city = cityMapper.fromDTO(cityDTO);
        cityService.updateCity(id, city);
        return new ResponseEntity<>(cityMapper.toDTO(city), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}/", "/{id}"})
    public ResponseEntity<Void> delete(@PathVariable String id) throws Exception {
        cityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
