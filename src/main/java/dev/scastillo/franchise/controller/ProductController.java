package dev.scastillo.franchise.controller;

import dev.scastillo.franchise.dtos.product.ProductDto;
import dev.scastillo.franchise.dtos.product.ProductRequestDto;
import dev.scastillo.franchise.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductRequestDto product) {
        return productService.createProduct(product);
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(
            @PathVariable int id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable int id, @RequestBody ProductRequestDto product) {
        return productService.updateProduct(id, product);
    }
}
