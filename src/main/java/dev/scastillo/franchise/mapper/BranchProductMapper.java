package dev.scastillo.franchise.mapper;

import dev.scastillo.franchise.dtos.branch_product.BranchProductDto;
import dev.scastillo.franchise.dtos.branch_product.BranchProductRequestDto;
import dev.scastillo.franchise.model.BranchProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchProductMapper {
    BranchProductDto toDto(BranchProduct branchProduct);

    BranchProduct toEntity(BranchProductRequestDto dto);
}
