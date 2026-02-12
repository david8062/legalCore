package com.IusCloud.legalCore.core.features.branch_schedules.application.usecase;

import com.IusCloud.legalCore.core.features.branch_schedules.application.dto.BranchScheduleRequestDTO;
import com.IusCloud.legalCore.core.features.branch_schedules.application.dto.BranchScheduleResponseDTO;
import com.IusCloud.legalCore.core.features.branch_schedules.application.mapper.BranchScheduleMapper;
import com.IusCloud.legalCore.core.features.branch_schedules.domain.model.BranchScheduleEntity;
import com.IusCloud.legalCore.core.features.branch_schedules.domain.repository.BranchScheduleRepository;
import com.IusCloud.legalCore.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateBranchScheduleUseCase {

    private final BranchScheduleRepository repository;
    private final BranchScheduleMapper mapper;

    @Transactional
    public BranchScheduleResponseDTO execute(UUID branchId, UUID scheduleId, BranchScheduleRequestDTO request) {

        BranchScheduleEntity entity = repository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch Schedule not found"));

        if (!entity.getBranch().getId().equals(branchId)) {
             throw new ResourceNotFoundException("Schedule does not belong to the specified branch");
        }

        mapper.updateEntityFromDto(request, entity);
        BranchScheduleEntity updatedEntity = repository.save(entity);
        return mapper.toResponse(updatedEntity);
    }
}