package dev.scastillo.franchise.controller;

import dev.scastillo.franchise.dtos.FranchiseDto;
import dev.scastillo.franchise.dtos.FranchiseRequestDto;
import dev.scastillo.franchise.service.FranchiseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/franchises")
@AllArgsConstructor
public class FranchiseController {
    private final FranchiseService franchiseService;
    
    @PostMapping
    public FranchiseDto create(@RequestBody FranchiseRequestDto request) {
        return franchiseService.create(request);
    }

    @GetMapping("/{id}")
    public FranchiseDto getById(@PathVariable int id) {
        return franchiseService.getById(id);
    }

    @GetMapping
    public List<FranchiseDto> getAll() {
        return franchiseService.getAll();
    }

    @PutMapping("/{id}")
    public FranchiseDto update(@PathVariable int id, @RequestBody FranchiseRequestDto request) {
        return franchiseService.update(id, request);
    }
}
