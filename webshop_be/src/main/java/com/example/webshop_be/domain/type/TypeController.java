package com.example.webshop_be.domain.type;

import com.example.webshop_be.domain.type.mapper.TypeMapper;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/types")
public class TypeController {

    private final TypeService typeService;

    private final TypeMapper typeMapper;

    @Autowired
    public TypeController(TypeService typeService, TypeMapper typeMapper) {
        this.typeService = typeService;
        this.typeMapper = typeMapper;
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<TypeDTO> getById(@PathVariable String id) {
        Type type = typeService.findById(id);
        return new ResponseEntity<>(typeMapper.toDTO(type), HttpStatus.OK);
    }

    @GetMapping({"", "/"})
    public @ResponseBody ResponseEntity<List<TypeDTO>> getAll() {
        List<Type> types = typeService.getAllTypes();
        return new ResponseEntity<>(typeMapper.toDTOs(types), HttpStatus.OK);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<TypeDTO> create(@Valid @RequestBody TypeDTO typeDTO) {
        Type type = typeService.createType(typeMapper.fromDTO(typeDTO));
        return new ResponseEntity<>(typeMapper.toDTO(type), HttpStatus.CREATED);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public ResponseEntity<TypeDTO> updateById(@PathVariable String id,
                                              @RequestBody TypeDTO typeDTO) throws Exception {
        Type type = typeMapper.fromDTO(typeDTO);
        typeService.updateType(id, type);
        return new ResponseEntity<>(typeMapper.toDTO(type), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        typeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
