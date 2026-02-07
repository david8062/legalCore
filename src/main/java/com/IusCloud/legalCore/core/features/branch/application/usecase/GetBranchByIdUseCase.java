package com.IusCloud.legalCore.core.features.branch.application.usecase;

import com.IusCloud.legalCore.core.features.branch.application.dto.BranchResponseDTO;
import com.IusCloud.legalCore.core.features.branch.application.mapper.BranchMapper;
import com.IusCloud.legalCore.core.features.branch.domain.model.BranchEntity;
import com.IusCloud.legalCore.core.features.branch.domain.repository.BranchRepository;
import com.IusCloud.legalCore.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetBranchByIdUseCase {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    @Transactional(readOnly = true)
    public BranchResponseDTO execute(UUID id, UUID tenantId) {
        BranchEntity entity = branchRepository.findById(id, tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found"));
        return branchMapper.toResponse(entity);
    }
}