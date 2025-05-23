package dev.scastillo.franchise.service;


import dev.scastillo.franchise.dtos.product.ProductDto;
import dev.scastillo.franchise.dtos.product.ProductRequestDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductRequestDto dto);

    ProductDto getProductById(Integer id);

    List<ProductDto> getAllProducts();

    ProductDto updateProduct(Integer id, ProductRequestDto dto);
    
}
