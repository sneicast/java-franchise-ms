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
public class BranchProductRequestDto {
    private Integer stock;
    private BigDecimal price;
}
