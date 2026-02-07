package com.IusCloud.legalCore.core.features.branch.domain.repository;

import com.IusCloud.legalCore.core.features.branch.domain.model.BranchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BranchRepository {

    BranchEntity save(BranchEntity branch);

    Optional<BranchEntity> findById(UUID id, UUID tenantId);

    List<BranchEntity> findAll(UUID tenantId);

    Page<BranchEntity> findAll(UUID tenantId, Pageable pageable);

    boolean existsByName(String name, UUID tenantId);

    boolean existsByEmail(String email, UUID tenantId);

    void delete(BranchEntity branch);
}