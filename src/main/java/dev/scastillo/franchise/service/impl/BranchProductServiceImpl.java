package dev.scastillo.franchise.service.impl;

import dev.scastillo.franchise.dtos.branch_product.BranchProductDto;
import dev.scastillo.franchise.dtos.branch_product.BranchProductRequestDto;
import dev.scastillo.franchise.exception.BadRequestException;
import dev.scastillo.franchise.mapper.BranchProductMapper;
import dev.scastillo.franchise.model.Branch;
import dev.scastillo.franchise.model.BranchProduct;
import dev.scastillo.franchise.model.BranchProductId;
import dev.scastillo.franchise.model.Product;
import dev.scastillo.franchise.repository.BranchProductRepository;
import dev.scastillo.franchise.repository.BranchRepository;
import dev.scastillo.franchise.repository.ProductRepository;
import dev.scastillo.franchise.service.BranchProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BranchProductServiceImpl implements BranchProductService {
    private final BranchProductRepository branchProductRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;
    private final BranchProductMapper branchProductMapper;

    @Override
    public BranchProductDto addProductToBranch(int branchId, int productId, BranchProductRequestDto branchProductRequestDto) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new BadRequestException("La sucursal con id " + branchId + " no fue encontrada"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BadRequestException("El producto con id " + productId + " no fue encontrado"));

        BranchProduct addBranchProduct = branchProductMapper.toEntity(branchProductRequestDto);
        addBranchProduct.setBranch(branch);
        addBranchProduct.setProduct(product);
        BranchProduct savedBranchProduct = branchProductRepository.save(addBranchProduct);
        return branchProductMapper.toDto(savedBranchProduct);
    }

    @Override
    public void removeProductFromBranch(int branchId, int productId) {
        BranchProductId branchProductId = new BranchProductId(branchId, productId);
        branchProductRepository.deleteById(branchProductId);
    }

    @Override
    public List<BranchProductDto> getProductsByBranchId(int branchId) {
        List<BranchProduct> branchProducts = branchProductRepository.findByBranchId(branchId);
        return branchProducts.stream().map(branchProductMapper::toDto)
                .toList();
    }

    @Override
    public BranchProductDto UpdateBranchProduct(int branchId, int productId, BranchProductRequestDto branchProductRequestDto) {
        return branchProductRepository.findById(new BranchProductId(branchId, productId))
                .map(existingBranchProduct -> {
                    existingBranchProduct.setPrice(branchProductRequestDto.getPrice());
                    existingBranchProduct.setStock(branchProductRequestDto.getStock());
                    BranchProduct updatedBranchProduct = branchProductRepository.save(existingBranchProduct);
                    return branchProductMapper.toDto(updatedBranchProduct);
                })
                .orElseThrow(() -> new BadRequestException("No se puede actualizar el producto de la sucursal con id: " + branchId + " y producto con id: " + productId));
    }

    @Override
    public List<BranchProductDto> getTopStockProductsByFranchise(int franchiseId) {
        return branchProductRepository.findTopStockProductsByFranchise(franchiseId);
    }
}
