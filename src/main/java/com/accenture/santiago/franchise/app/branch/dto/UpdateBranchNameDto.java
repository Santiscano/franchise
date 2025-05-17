package com.accenture.santiago.franchise.app.branch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBranchNameDto {

    @NotBlank(message = "Branch name is required, cannot be blank")
    @NotNull(message = "Branch name is required, cannot be null")
    @NotEmpty(message = "Branch name is required, cannot be empty")
    public String nombreSucursal;
}
