package com.IusCloud.legalCore.core.features.branch_schedules.application.usecase;

import com.IusCloud.legalCore.core.features.branch_schedules.application.dto.BranchScheduleResponseDTO;
import com.IusCloud.legalCore.core.features.branch_schedules.application.mapper.BranchScheduleMapper;
import com.IusCloud.legalCore.core.features.branch_schedules.domain.model.BranchScheduleEntity;
import com.IusCloud.legalCore.core.features.branch_schedules.domain.repository.BranchScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetBranchSchedulesByBranchUseCase {

    private final BranchScheduleRepository repository;
    private final BranchScheduleMapper mapper;

    @Transactional(readOnly = true)
    public List<BranchScheduleResponseDTO> execute(UUID branchId) {
        List<BranchScheduleEntity> schedules = repository.findAllByBranchId(branchId);
        return schedules.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}