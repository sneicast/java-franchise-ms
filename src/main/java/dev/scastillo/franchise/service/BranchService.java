package dev.scastillo.franchise.service;

import dev.scastillo.franchise.dtos.branch.BranchDto;
import dev.scastillo.franchise.dtos.branch.BranchRequestDto;

import java.util.List;


public interface BranchService {
    BranchDto createBranch(BranchRequestDto branch);

    BranchDto getBranchById(int id);

    List<BranchDto> getAllBranches();

    BranchDto updateBranch(int id, BranchRequestDto branch);

    List<BranchDto> getByFranchiseId(int franchiseId);
}
