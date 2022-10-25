package com.example.webshop_be.domain.product;

import com.example.webshop_be.domain.product.mapper.ProductMapper;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@CrossOrigin("http://localhost:3000")
public class ProductController {

    private final ProductService productService;

    private final ProductMapper mapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<ProductDTO> getById(@PathVariable String id) {
        Product product = productService.findById(id);
        return new ResponseEntity<>(mapper.toDTO(product), HttpStatus.OK);
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<ProductDTO>> getAll() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(mapper.toDTOs(products), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}/", "/{id}"})
    public ResponseEntity<Void> delete(@PathVariable String id) {
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO)
            throws Exception {
        Product product = productService.createProduct(mapper.fromDTO(productDTO));
        return new ResponseEntity<>(mapper.toDTO(product), HttpStatus.CREATED);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public ResponseEntity<ProductDTO> updateById(@PathVariable String id,
                                                 @RequestBody ProductDTO productDTO)
            throws Exception {
        Product product = mapper.fromDTO(productDTO);
        productService.updateProduct(id, product);
        return new ResponseEntity<>(mapper.toDTO(product), HttpStatus.OK);
    }
}
