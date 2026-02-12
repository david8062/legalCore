package com.IusCloud.legalCore.core.features.branch_schedules.application.mapper;

import com.IusCloud.legalCore.config.mapper.BaseEntityMapperConfig;
import com.IusCloud.legalCore.core.features.branch_schedules.application.dto.BranchScheduleRequestDTO;
import com.IusCloud.legalCore.core.features.branch_schedules.application.dto.BranchScheduleResponseDTO;
import com.IusCloud.legalCore.core.features.branch_schedules.domain.model.BranchScheduleEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        config = BaseEntityMapperConfig.class
)
public interface BranchScheduleMapper {

    @Mapping(target = "branch.id", ignore = true)
    @InheritConfiguration(name = "baseToEntityMapping")
    BranchScheduleEntity toEntity(BranchScheduleRequestDTO dto);

    @Mapping(target = "branchId", source = "branch.id")
    BranchScheduleResponseDTO toResponse(BranchScheduleEntity entity);

    @Mapping(target = "branch.id", ignore = true)
    @InheritConfiguration(name = "baseToEntityMapping")
    void updateEntityFromDto(BranchScheduleRequestDTO dto, @MappingTarget BranchScheduleEntity entity);
}