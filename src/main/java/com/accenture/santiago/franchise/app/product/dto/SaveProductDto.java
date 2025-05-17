package com.accenture.santiago.franchise.app.product.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveProductDto {

    @NotBlank(message = "Sucursal ID is required, cannot be blank")
    @NotNull(message = "Sucursal ID is required, cannot be null")
    @NotEmpty(message = "Sucursal ID is required, cannot be empty")
    @Positive(message = "Sucursal ID must be a positive number")
    private Integer sucursalId;

    @NotBlank(message = "Product name is required, cannot be blank")
    @NotNull(message = "Product name is required, cannot be null")
    @NotEmpty(message = "Product name is required, cannot be empty")
    private String nombre;

    @PositiveOrZero(message = "Stock must be a positive number or zero")
    @NotNull(message = "Stock is required, cannot be null")
    @NotBlank(message = "Stock is required, cannot be blank")
    @NotEmpty(message = "Stock is required, cannot be empty")
    private Long existencias;
}
