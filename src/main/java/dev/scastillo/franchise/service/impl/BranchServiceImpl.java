package dev.scastillo.franchise.service.impl;

import dev.scastillo.franchise.dtos.branch.BranchDto;
import dev.scastillo.franchise.dtos.branch.BranchRequestDto;
import dev.scastillo.franchise.exception.BadRequestException;
import dev.scastillo.franchise.exception.NotFoundException;
import dev.scastillo.franchise.mapper.BranchMapper;
import dev.scastillo.franchise.model.Branch;
import dev.scastillo.franchise.model.Franchise;
import dev.scastillo.franchise.repository.BranchRepository;
import dev.scastillo.franchise.repository.FranchiseRepository;
import dev.scastillo.franchise.service.BranchService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;
    private final FranchiseRepository franchiseRepository;

    @Override
    public BranchDto createBranch(BranchRequestDto branch) {
        Franchise franchise = franchiseRepository.findById(branch.getFranchiseId())
                .orElseThrow(() -> new NotFoundException("La Franquicia con id " + branch.getFranchiseId() + " no fue encontrada"));
        Branch newBranch = branchMapper.toEntity(branch);
        newBranch.setFranchise(franchise);
        Branch savedBranch = branchRepository.save(newBranch);
        return branchMapper.toDto(savedBranch);
    }

    @Override
    @Cacheable(value = "branches", key = "#id")
    public BranchDto getBranchById(int id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro la Sucursal con id: " + id));
        return branchMapper.toDto(branch);
    }

    @Override
    public List<BranchDto> getAllBranches() {
        return branchRepository.findAll().stream()
                .map(branchMapper::toDto)
                .toList();
    }

    @Override
    @CachePut(value = "branches", key = "#id")
    public BranchDto updateBranch(int id, BranchRequestDto branch) {
        return branchRepository.findById(id)
                .map(existingBranch -> {
                    existingBranch.setName(branch.getName());
                    existingBranch.setStatus(branch.isStatus());
                    Branch updatedBranch = branchRepository.save(existingBranch);
                    return branchMapper.toDto(updatedBranch);
                })
                .orElseThrow(() -> new BadRequestException("No se puede actualizar la Sucursal con id: " + id));
    }

    @Override
    public List<BranchDto> getByFranchiseId(int franchiseId) {
        return branchRepository.findByFranchiseId(franchiseId).stream().map(branchMapper::toDto).toList();
    }
}
