package dev.scastillo.franchise.mapper;

import dev.scastillo.franchise.dtos.product.ProductDto;
import dev.scastillo.franchise.dtos.product.ProductRequestDto;
import dev.scastillo.franchise.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);

    Product toEntity(ProductRequestDto dto);
}
