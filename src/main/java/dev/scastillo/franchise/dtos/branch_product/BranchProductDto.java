package dev.scastillo.franchise.dtos.branch_product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BranchProductDto {
    private int branchId;
    private String branchName;
    private int productId;
    private String productName;
    private Integer stock;
    private BigDecimal price;
}
