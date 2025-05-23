package dev.scastillo.franchise.service.impl;

import dev.scastillo.franchise.dtos.product.ProductDto;
import dev.scastillo.franchise.dtos.product.ProductRequestDto;
import dev.scastillo.franchise.exception.BadRequestException;
import dev.scastillo.franchise.exception.NotFoundException;
import dev.scastillo.franchise.mapper.ProductMapper;
import dev.scastillo.franchise.model.Product;
import dev.scastillo.franchise.repository.ProductRepository;
import dev.scastillo.franchise.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto createProduct(ProductRequestDto dto) {
        Product newProduct = productMapper.toEntity(dto);
        Product savedProduct = productRepository.save(newProduct);
        return productMapper.toDto(savedProduct);
    }

    @Override
    public ProductDto getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro el Producto con id: " + id));
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ProductDto updateProduct(Integer id, ProductRequestDto dto) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(dto.getName());
                    existingProduct.setStatus(dto.isStatus());
                    Product updatedProduct = productRepository.save(existingProduct);
                    return productMapper.toDto(updatedProduct);
                })
                .orElseThrow(() -> new BadRequestException("No se puede actualizar el Producto con id: " + id));
    }
}
