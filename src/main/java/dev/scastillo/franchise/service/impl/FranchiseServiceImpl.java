package dev.scastillo.franchise.service.impl;

import dev.scastillo.franchise.dtos.FranchiseDto;
import dev.scastillo.franchise.dtos.FranchiseRequestDto;
import dev.scastillo.franchise.exception.BadRequestException;
import dev.scastillo.franchise.exception.NotFoundException;
import dev.scastillo.franchise.mapper.FranchiseMapper;
import dev.scastillo.franchise.model.Franchise;
import dev.scastillo.franchise.repository.FranchiseRepository;
import dev.scastillo.franchise.service.FranchiseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseMapper franchiseMapper;
    private final FranchiseRepository franchiseRepository;

    @Override
    public FranchiseDto create(FranchiseRequestDto dto) {
        Franchise franchise = franchiseMapper.toEntity(dto);

        Franchise savedFranchise = franchiseRepository.save(franchise);
        return franchiseMapper.toDto(savedFranchise);
    }

    @Override
    public FranchiseDto getById(Integer id) {
        Franchise franchise = franchiseRepository.findById(id).orElseThrow(() -> new NotFoundException("La franquincia con id " + id + " no fue encontrada"));
        return franchiseMapper.toDto(franchise);
    }

    @Override
    public List<FranchiseDto> getAll() {
        List<Franchise> franchise = franchiseRepository.findAll();
        return franchiseRepository.findAll().stream()
                .map(franchiseMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public FranchiseDto update(Integer id, FranchiseRequestDto dto) {
        return franchiseRepository.findById(id)
                .map(existingFranchise -> {
                    existingFranchise.setName(dto.getName());
                    Franchise updatedFranchise = franchiseRepository.save(existingFranchise);
                    return franchiseMapper.toDto(updatedFranchise);
                })
                .orElseThrow(() -> new BadRequestException("No fue posible actualizar la franquicia con id " + id));
    }
}
