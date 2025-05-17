package com.accenture.santiago.franchise.app.product.dto;

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
public class UpdateProductNameDto {

    @NotBlank(message = "Product name is required, cannot be blank")
    @NotNull(message = "Product name is required, cannot be null")
    @NotEmpty(message = "Product name is required, cannot be empty")
    private String nombre;
}
