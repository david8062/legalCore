package com.IusCloud.legalCore.core.features.branch.application.usecase;

import com.IusCloud.legalCore.core.features.branch.application.dto.BranchRequestDTO;
import com.IusCloud.legalCore.core.features.branch.application.dto.BranchResponseDTO;
import com.IusCloud.legalCore.core.features.branch.application.mapper.BranchMapper;
import com.IusCloud.legalCore.core.features.branch.domain.model.BranchEntity;
import com.IusCloud.legalCore.core.features.branch.domain.repository.BranchRepository;
import com.IusCloud.legalCore.shared.exceptions.BusinessException;
import com.IusCloud.legalCore.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateBranchUseCase {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    @Transactional
    public BranchResponseDTO execute(UUID id, BranchRequestDTO request, UUID tenantId) {
        BranchEntity entity = branchRepository.findById(id, tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found"));

        if (!entity.getName().equals(request.getName()) && branchRepository.existsByName(request.getName(), tenantId)) {
            throw new BusinessException("Branch with name " + request.getName() + " already exists");
        }
        if (!entity.getEmail().equals(request.getEmail()) && branchRepository.existsByEmail(request.getEmail(), tenantId)) {
            throw new BusinessException("Branch with email " + request.getEmail() + " already exists");
        }

        branchMapper.updateEntityFromDto(request, entity);
        BranchEntity updatedEntity = branchRepository.save(entity);
        return branchMapper.toResponse(updatedEntity);
    }
}