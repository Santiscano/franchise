package com.accenture.santiago.franchise.app.product.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table("product")
public class ProductEntity {

    @Id
    private Long idProduct;

    private String name;
    private Long stock;
}
