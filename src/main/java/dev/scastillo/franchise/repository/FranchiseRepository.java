package dev.scastillo.franchise.repository;

import dev.scastillo.franchise.model.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {
}
