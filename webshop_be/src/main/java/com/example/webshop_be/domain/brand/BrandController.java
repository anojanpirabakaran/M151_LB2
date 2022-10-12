package com.example.webshop_be.domain.brand;

import com.example.webshop_be.domain.brand.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brands")
@CrossOrigin("http://localhost:3000")
public class BrandController {

    private final BrandService brandService;
    private final BrandMapper brandMapper;

    @Autowired
    public BrandController(BrandService brandService, BrandMapper brandMapper) {
        this.brandService = brandService;
        this.brandMapper = brandMapper;
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<BrandDTO> findById(@PathVariable String id) {
        Brand brand = brandService.findById(id);
        return new ResponseEntity<>(brandMapper.toDTO(brand), HttpStatus.FOUND);
    }

    @DeleteMapping({"/{id}/", "/{id}"})
    public ResponseEntity<Void> delete(@PathVariable String id) throws Exception {
        brandService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping({"/", ""})
    public ResponseEntity<BrandDTO> create(@RequestBody BrandDTO brandDTO)
            throws Exception {
        Brand brand = brandService.createBrand(brandMapper.fromDTO(brandDTO));
        return new ResponseEntity<>(brandMapper.toDTO(brand), HttpStatus.CREATED);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public ResponseEntity<BrandDTO> updateById(@PathVariable String id,
                                               @RequestBody BrandDTO brandDTO)
            throws Exception {
        Brand brand = brandMapper.fromDTO(brandDTO);
        brandService.updateBrand(id, brand);
        return new ResponseEntity<>(brandMapper.toDTO(brand), HttpStatus.OK);
    }
}
