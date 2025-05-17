package com.accenture.santiago.franchise.app.franchise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveFranchiseDto {

    @NotBlank(message = "Franchise name is required, cannot be blank")
    @NotEmpty(message = "Franchise name is required, cannot be empty")
    @NotNull(message = "Franchise name is required, cannot be null")
    public String nombreFranquicia;
}
