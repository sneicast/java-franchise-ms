package dev.scastillo.franchise.mapper;

import dev.scastillo.franchise.dtos.branch.BranchDto;
import dev.scastillo.franchise.dtos.branch.BranchRequestDto;
import dev.scastillo.franchise.model.Branch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    BranchDto toDto(Branch branch);

    Branch toEntity(BranchRequestDto dto);
}
