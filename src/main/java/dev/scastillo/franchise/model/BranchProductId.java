package dev.scastillo.franchise.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BranchProductId implements Serializable {
    private Integer branchId;
    private Integer productId;

    // Constructor vacío, equals y hashCode obligatorios

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BranchProductId that)) return false;
        return Objects.equals(branchId, that.branchId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(branchId, productId);
    }

}

