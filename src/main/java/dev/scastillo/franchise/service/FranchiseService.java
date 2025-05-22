package dev.scastillo.franchise.service;

import dev.scastillo.franchise.dtos.FranchiseDto;
import dev.scastillo.franchise.dtos.FranchiseRequestDto;

import java.util.List;

public interface FranchiseService {


    FranchiseDto create(FranchiseRequestDto dto);

    FranchiseDto getById(Integer id);

    List<FranchiseDto> getAll();

    FranchiseDto update(Integer id, FranchiseRequestDto dto);
}
