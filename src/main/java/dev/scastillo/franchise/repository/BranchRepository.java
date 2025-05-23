package dev.scastillo.franchise.repository;

import dev.scastillo.franchise.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
    List<Branch> findByFranchiseId(int franchiseId);
}
