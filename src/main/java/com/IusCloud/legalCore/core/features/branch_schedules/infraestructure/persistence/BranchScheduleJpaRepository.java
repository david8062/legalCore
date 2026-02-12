package com.IusCloud.legalCore.core.features.branch_schedules.infraestructure.persistence;

import com.IusCloud.legalCore.core.base.BaseRepository;
import com.IusCloud.legalCore.core.features.branch_schedules.domain.model.BranchScheduleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BranchScheduleJpaRepository extends BaseRepository<BranchScheduleEntity, UUID> {

    List<BranchScheduleEntity> findAllByBranchIdAndIsActiveTrue(UUID branchId);

    Page<BranchScheduleEntity> findAllByIsActiveTrue(Pageable pageable);
}