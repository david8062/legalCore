package com.IusCloud.legalCore.core.features.branch_schedules.domain.repository;

import com.IusCloud.legalCore.core.features.branch_schedules.domain.model.BranchScheduleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BranchScheduleRepository {

    BranchScheduleEntity save(BranchScheduleEntity schedule);

    Optional<BranchScheduleEntity> findById(UUID id);

    List<BranchScheduleEntity> findAllByBranchId(UUID branchId);

    Page<BranchScheduleEntity> findAll(Pageable pageable);

    void delete(BranchScheduleEntity schedule);
}