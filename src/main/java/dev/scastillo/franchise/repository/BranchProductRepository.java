package dev.scastillo.franchise.repository;

import dev.scastillo.franchise.model.BranchProduct;
import dev.scastillo.franchise.model.BranchProductId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchProductRepository extends JpaRepository<BranchProduct, BranchProductId> {
    List<BranchProduct> findByBranchId(int branchId);
}
