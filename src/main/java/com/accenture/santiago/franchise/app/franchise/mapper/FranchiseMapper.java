package com.accenture.santiago.franchise.app.franchise.mapper;

import com.accenture.santiago.franchise.app.franchise.dto.SaveFranchiseDto;
import com.accenture.santiago.franchise.app.franchise.dto.UpdateFranchiseNameDto;
import com.accenture.santiago.franchise.app.franchise.entity.FranchiseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FranchiseMapper {

    @Mappings({
        @Mapping(target = "idFranchise", ignore = true),
        @Mapping(target = "name", source = "nombreFranquicia")
    })
    FranchiseEntity toEntitySave(SaveFranchiseDto dto);

    @Mappings({
            @Mapping(target = "idFranchise", ignore = true),
            @Mapping(target = "name", source = "nombreFranquicia")
    })
    FranchiseEntity toEntityUpdate(UpdateFranchiseNameDto dto); // no uso el mismo dto, pues esta entidad podria crecer en el futuro

}
