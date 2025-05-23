package dev.scastillo.franchise.dtos.branch;

import dev.scastillo.franchise.dtos.FranchiseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BranchDto {
    private int id;

    private String name;

    private boolean status;

    private FranchiseDto franchise;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
