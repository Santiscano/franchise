package com.accenture.santiago.franchise.app.franchise.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("franchise")
public class FranchiseEntity {

    @Id
    private Integer idFranchise;

    private String name;
    private Boolean isActive;
}
