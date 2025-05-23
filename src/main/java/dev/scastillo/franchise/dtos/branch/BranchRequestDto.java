package dev.scastillo.franchise.dtos.branch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BranchRequestDto {

    private String name;

    private boolean status;

    private int franchiseId;
}
