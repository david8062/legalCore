package com.IusCloud.legalCore.core.features.branch.infraestructure.persistence;

import com.IusCloud.legalCore.core.base.BaseRepository;
import com.IusCloud.legalCore.core.features.branch.domain.model.BranchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BranchJpaRepository
        extends BaseRepository<BranchEntity, UUID> {

    Optional<BranchEntity> findByIdAndTenantId(UUID id, UUID tenantId);

    List<BranchEntity> findAllByTenantId(UUID tenantId);

    Page<BranchEntity> findAllByTenantId(UUID tenantId, Pageable pageable);

    Page<BranchEntity> findAllByTenantIdAndIsActiveTrue(UUID tenantId, Pageable pageable);

    boolean existsByNameAndTenantId(String name, UUID tenantId);

    boolean existsByEmailAndTenantId(String email, UUID tenantId);
}