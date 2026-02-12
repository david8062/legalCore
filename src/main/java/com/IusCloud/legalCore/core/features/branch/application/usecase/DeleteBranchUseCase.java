package com.IusCloud.legalCore.core.features.branch.application.usecase;

import com.IusCloud.legalCore.core.features.branch.domain.model.BranchEntity;
import com.IusCloud.legalCore.core.features.branch.domain.repository.BranchRepository;
import com.IusCloud.legalCore.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteBranchUseCase {

    private final BranchRepository branchRepository;

    @Transactional
    public void execute(UUID id, UUID tenantId) {
        BranchEntity entity = branchRepository.findById(id, tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found"));
        
        // Soft delete
        entity.setIsActive(false);
        entity.setDeletedAt(Instant.now());
        
        branchRepository.save(entity);
    }
}