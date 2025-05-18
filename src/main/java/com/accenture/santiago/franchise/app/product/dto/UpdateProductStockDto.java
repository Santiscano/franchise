package com.accenture.santiago.franchise.app.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductStockDto {

    @NotNull(message = "Stock is required, cannot be null")
    private Long existencias;
}
