package dev.scastillo.franchise.controller;

import dev.scastillo.franchise.dtos.branch_product.BranchProductDto;
import dev.scastillo.franchise.dtos.branch_product.BranchProductRequestDto;
import dev.scastillo.franchise.service.BranchProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class BranchProductController {
    private final BranchProductService branchProductService;

    @PostMapping("/branches/{branchId}/products/{productId}")
    public BranchProductDto addProductToBranch(@PathVariable int branchId, @PathVariable int productId, @RequestBody BranchProductRequestDto request) {
        return branchProductService.addProductToBranch(branchId, productId, request);
    }

    @GetMapping("/branches/{branchId}/products")
    public List<BranchProductDto> getProductsByBranchId(@PathVariable int branchId) {
        return branchProductService.getProductsByBranchId(branchId);
    }

    @PutMapping("/branches/{branchId}/products/{productId}")
    public BranchProductDto updateBranchProduct(@PathVariable int branchId, @PathVariable int productId, @RequestBody BranchProductRequestDto request) {
        return branchProductService.UpdateBranchProduct(branchId, productId, request);
    }

    @DeleteMapping("/branches/{branchId}/products/{productId}")
    public void removeProductFromBranch(@PathVariable int branchId, @PathVariable int productId) {
        branchProductService.removeProductFromBranch(branchId, productId);
    }

    @GetMapping("/franchises/{franchiseId}/top-stock-products")
    public List<BranchProductDto> getTopStockProducts(@PathVariable int franchiseId) {
        return branchProductService.getTopStockProductsByFranchise(franchiseId);
    }


}
