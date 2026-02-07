package com.IusCloud.legalCore.core.features.branch.infraestructure.adapter;

import com.IusCloud.legalCore.core.features.branch.domain.model.BranchEntity;
import com.IusCloud.legalCore.core.features.branch.domain.repository.BranchRepository;
import com.IusCloud.legalCore.core.features.branch.infraestructure.persistence.BranchJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BranchRepositoryImpl implements BranchRepository {

    private final BranchJpaRepository jpaRepository;

    @Override
    public BranchEntity save(BranchEntity branch) {
        return jpaRepository.save(branch);
    }

    @Override
    public Optional<BranchEntity> findById(UUID id, UUID tenantId) {
        return jpaRepository.findByIdAndTenantId(id, tenantId);
    }

    @Override
    public List<BranchEntity> findAll(UUID tenantId) {
        return jpaRepository.findAllByTenantId(tenantId);
    }

    @Override
    public Page<BranchEntity> findAll(UUID tenantId, Pageable pageable) {
        return jpaRepository.findAllByTenantId(tenantId, pageable);
    }

    @Override
    public boolean existsByName(String name, UUID tenantId) {
        return jpaRepository.existsByNameAndTenantId(name, tenantId);
    }

    @Override
    public boolean existsByEmail(String email, UUID tenantId) {
        return jpaRepository.existsByEmailAndTenantId(email, tenantId);
    }

    @Override
    public void delete(BranchEntity branch) {
        jpaRepository.delete(branch);
    }
}