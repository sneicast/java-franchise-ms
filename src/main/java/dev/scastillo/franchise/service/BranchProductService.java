package dev.scastillo.franchise.service;

import dev.scastillo.franchise.dtos.branch_product.BranchProductDto;
import dev.scastillo.franchise.dtos.branch_product.BranchProductRequestDto;

import java.util.List;

public interface BranchProductService {
    BranchProductDto addProductToBranch(int branchId, int productId, BranchProductRequestDto branchProductRequestDto);

    void removeProductFromBranch(int branchId, int productId);

    List<BranchProductDto> getProductsByBranchId(int branchId);

    BranchProductDto UpdateBranchProduct(int branchId, int productId, BranchProductRequestDto branchProductRequestDto);


}
