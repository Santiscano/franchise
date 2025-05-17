package com.accenture.santiago.franchise.app.branch.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("branch") // sucursal
public class BranchEntity {

    @Id
    private Integer idBranch;

    private Integer franchiseId;
    private String name;
}
