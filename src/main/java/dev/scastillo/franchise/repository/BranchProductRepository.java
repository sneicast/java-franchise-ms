package dev.scastillo.franchise.repository;

import dev.scastillo.franchise.dtos.branch_product.BranchProductDto;
import dev.scastillo.franchise.model.BranchProduct;
import dev.scastillo.franchise.model.BranchProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BranchProductRepository extends JpaRepository<BranchProduct, BranchProductId> {
    List<BranchProduct> findByBranchId(int branchId);

    @Query(value = """
            SELECT bp.branch_id AS branchId,
                   b.name AS branchName,
                   bp.product_id AS productId,
                   p.name AS productName,
                   bp.stock AS stock,
                   bp.price AS price
            FROM branch_products bp
            JOIN branches b ON bp.branch_id = b.id
            JOIN products p ON bp.product_id = p.id
            JOIN (
                SELECT bp2.branch_id, MAX(bp2.stock) AS max_stock
                FROM branch_products bp2
                JOIN branches b2 ON bp2.branch_id = b2.id
                WHERE b2.franchise_id = :franchiseId
                GROUP BY bp2.branch_id
            ) max_bp ON max_bp.branch_id = bp.branch_id AND max_bp.max_stock = bp.stock
            WHERE b.franchise_id = :franchiseId
            """, nativeQuery = true)
    List<BranchProductDto> findTopStockProductsByFranchise(@Param("franchiseId") int franchiseId);

}
