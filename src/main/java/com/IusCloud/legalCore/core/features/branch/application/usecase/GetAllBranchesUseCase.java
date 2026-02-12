package com.IusCloud.legalCore.core.features.branch.application.usecase;

import com.IusCloud.legalCore.core.features.branch.application.dto.BranchResponseDTO;
import com.IusCloud.legalCore.core.features.branch.application.mapper.BranchMapper;
import com.IusCloud.legalCore.core.features.branch.domain.model.BranchEntity;
import com.IusCloud.legalCore.core.features.branch.domain.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetAllBranchesUseCase {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    @Transactional(readOnly = true)
    public Page<BranchResponseDTO> execute(UUID tenantId, Pageable pageable) {
        Page<BranchEntity> branches = branchRepository.findAllActive(tenantId, pageable);
        return branches.map(branchMapper::toResponse);
    }
}