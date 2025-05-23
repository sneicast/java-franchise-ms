package dev.scastillo.franchise.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
