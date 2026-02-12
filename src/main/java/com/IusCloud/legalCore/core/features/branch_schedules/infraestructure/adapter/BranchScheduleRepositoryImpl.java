package com.IusCloud.legalCore.core.features.branch_schedules.infraestructure.adapter;

import com.IusCloud.legalCore.core.features.branch_schedules.domain.model.BranchScheduleEntity;
import com.IusCloud.legalCore.core.features.branch_schedules.domain.repository.BranchScheduleRepository;
import com.IusCloud.legalCore.core.features.branch_schedules.infraestructure.persistence.BranchScheduleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BranchScheduleRepositoryImpl implements BranchScheduleRepository {

    private final BranchScheduleJpaRepository jpaRepository;

    @Override
    public BranchScheduleEntity save(BranchScheduleEntity schedule) {
        return jpaRepository.save(schedule);
    }

    @Override
    public Optional<BranchScheduleEntity> findById(UUID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<BranchScheduleEntity> findAllByBranchId(UUID branchId) {
        return jpaRepository.findAllByBranchIdAndIsActiveTrue(branchId);
    }

    @Override
    public Page<BranchScheduleEntity> findAll(Pageable pageable) {
        return jpaRepository.findAllByIsActiveTrue(pageable);
    }

    @Override
    public void delete(BranchScheduleEntity schedule) {
        jpaRepository.delete(schedule);
    }
}