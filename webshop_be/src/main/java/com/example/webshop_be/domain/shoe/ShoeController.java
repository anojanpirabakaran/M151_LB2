package com.example.webshop_be.domain.shoe;

import com.example.webshop_be.domain.shoe.mapper.ShoeMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoes")
public class ShoeController {

    private final ShoeService shoeService;
    private final ShoeMapper shoeMapper;

    @Autowired
    public ShoeController(ShoeService shoeService, ShoeMapper shoeMapper) {
        this.shoeService = shoeService;
        this.shoeMapper = shoeMapper;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<ShoeDTO>> getAll() {
        List<Shoe> shoes = shoeService.getAllShoes();
        return new ResponseEntity<>(shoeMapper.toDTOs(shoes), HttpStatus.OK);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<ShoeDTO> getById(@PathVariable String id) {
        Shoe shoe = shoeService.findById(id);
        return new ResponseEntity<>(shoeMapper.toDTO(shoe), HttpStatus.OK);
    }
}
