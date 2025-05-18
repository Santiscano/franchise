package com.accenture.santiago.franchise.app.branch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveBranchDto {

    @NotBlank(message = "Branch name is required, cannot be blank")
    @NotNull(message = "Branch name is required, cannot be null")
    @NotEmpty(message = "Branch name is required, cannot be empty")
    private String nombreSucursal;

    @NotNull(message = "franquiciaId name is required, cannot be null")
    @Positive(message = "franquiciaId name must be positive")
    private Integer franquiciaId;
}
