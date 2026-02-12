package com.IusCloud.legalCore.core.features.branch.application.mapper;

import com.IusCloud.legalCore.config.mapper.BaseEntityMapperConfig;
import com.IusCloud.legalCore.core.features.branch.application.dto.BranchRequestDTO;
import com.IusCloud.legalCore.core.features.branch.application.dto.BranchResponseDTO;
import com.IusCloud.legalCore.core.features.branch.domain.model.BranchEntity;
import org.mapstruct.*;


@Mapper(
        componentModel = "spring",
        config = BaseEntityMapperConfig.class
)

public interface BranchMapper {

    // ======================
    // Request → Entity
    // ======================
    @Mapping(target = "tenantId", ignore = true)
    @InheritConfiguration(name = "baseToEntityMapping")
    BranchEntity toEntity(BranchRequestDTO dto);

    // ======================
    // Entity → Response
    // ======================
    BranchResponseDTO toResponse(BranchEntity entity);

    // ======================
    // Update (merge)
    // ======================
    @InheritConfiguration(name = "baseToEntityMapping")
    void updateEntityFromDto(
            BranchRequestDTO dto,
            @MappingTarget BranchEntity entity
    );
}