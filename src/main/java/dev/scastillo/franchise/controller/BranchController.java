package dev.scastillo.franchise.controller;

import dev.scastillo.franchise.dtos.branch.BranchDto;
import dev.scastillo.franchise.dtos.branch.BranchRequestDto;
import dev.scastillo.franchise.service.BranchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@AllArgsConstructor
public class BranchController {
    private final BranchService branchService;

    @PostMapping
    public BranchDto createBranch(@RequestBody BranchRequestDto newBranch) {
        return branchService.createBranch(newBranch);
    }

    @GetMapping
    public List<BranchDto> getAllBranches() {
        return branchService.getAllBranches();
    }

    @GetMapping("/{id}")
    public BranchDto getBranchById(@PathVariable int id) {
        return branchService.getBranchById(id);
    }

    @PutMapping("/{id}")
    public BranchDto updateBranch(@PathVariable int id, @RequestBody BranchRequestDto branch) {
        return branchService.updateBranch(id, branch);
    }

    @GetMapping("/franchise/{franchiseId}")
    public List<BranchDto> getByFranchiseId(@PathVariable int franchiseId) {
        return branchService.getByFranchiseId(franchiseId);
    }

}
