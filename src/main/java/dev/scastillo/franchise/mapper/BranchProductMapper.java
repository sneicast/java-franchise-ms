package dev.scastillo.franchise.mapper;

import dev.scastillo.franchise.dtos.branch_product.BranchProductDto;
import dev.scastillo.franchise.dtos.branch_product.BranchProductRequestDto;
import dev.scastillo.franchise.model.BranchProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BranchProductMapper {
    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "branch.name", target = "branchName")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    BranchProductDto toDto(BranchProduct branchProduct);

    BranchProduct toEntity(BranchProductRequestDto dto);
}
