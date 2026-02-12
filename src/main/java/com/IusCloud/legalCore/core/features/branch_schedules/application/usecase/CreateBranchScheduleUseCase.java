package com.IusCloud.legalCore.core.features.branch_schedules.application.usecase;

import com.IusCloud.legalCore.core.features.branch.domain.model.BranchEntity;
import com.IusCloud.legalCore.core.features.branch.domain.repository.BranchRepository;
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
public class CreateBranchScheduleUseCase {

    private final BranchScheduleRepository repository;
    private final BranchRepository branchRepository;
    private final BranchScheduleMapper mapper;

    @Transactional
    public BranchScheduleResponseDTO execute(UUID branchId, BranchScheduleRequestDTO request, UUID tenantId) {
        // Buscar la entidad Branch para asignarla, validando que pertenezca al tenant
        BranchEntity branch = branchRepository.findById(branchId, tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found"));

        BranchScheduleEntity entity = mapper.toEntity(request);
        entity.setBranch(branch);

        BranchScheduleEntity savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }
}