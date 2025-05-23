package dev.scastillo.franchise.mapper;

import dev.scastillo.franchise.dtos.franchise.FranchiseDto;
import dev.scastillo.franchise.dtos.franchise.FranchiseRequestDto;
import dev.scastillo.franchise.model.Franchise;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FranchiseMapper {
    FranchiseDto toDto(Franchise franchise);

    Franchise toEntity(FranchiseRequestDto dto);
}
