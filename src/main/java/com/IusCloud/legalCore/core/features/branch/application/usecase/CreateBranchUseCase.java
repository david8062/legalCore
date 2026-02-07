package com.IusCloud.legalCore.core.features.branch.application.usecase;

import com.IusCloud.legalCore.core.features.branch.application.dto.BranchRequestDTO;
import com.IusCloud.legalCore.core.features.branch.application.dto.BranchResponseDTO;
import com.IusCloud.legalCore.core.features.branch.application.mapper.BranchMapper;
import com.IusCloud.legalCore.core.features.branch.domain.model.BranchEntity;
import com.IusCloud.legalCore.core.features.branch.domain.repository.BranchRepository;
import com.IusCloud.legalCore.shared.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateBranchUseCase {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    @Transactional
    public BranchResponseDTO execute(BranchRequestDTO request, UUID tenantId) {
        if (branchRepository.existsByName(request.getName(), tenantId)) {
            throw new BusinessException("Branch with name " + request.getName() + " already exists");
        }
        if (branchRepository.existsByEmail(request.getEmail(), tenantId)) {
            throw new BusinessException("Branch with email " + request.getEmail() + " already exists");
        }

        BranchEntity entity = branchMapper.toEntity(request);
        entity.setTenantId(tenantId);

        BranchEntity savedEntity = branchRepository.save(entity);
        return branchMapper.toResponse(savedEntity);
    }
}