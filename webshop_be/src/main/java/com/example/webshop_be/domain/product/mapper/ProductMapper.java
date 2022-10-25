package com.example.webshop_be.domain.product.mapper;


import com.example.webshop_be.domain.product.Product;
import com.example.webshop_be.domain.product.ProductDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product fromDTO(ProductDTO productDTO);

    List<Product> fromDTOs(List<ProductDTO> productDTOS);

    ProductDTO toDTO(Product product);

    List<ProductDTO> toDTOs(List<Product> products);
}
