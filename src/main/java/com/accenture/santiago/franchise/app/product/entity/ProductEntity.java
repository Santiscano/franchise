package com.accenture.santiago.franchise.app.product.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("product")
public class ProductEntity {

    @Id
    private Integer idProduct;

    private Integer branchId;
    private String name;
    private Long stock;
}
