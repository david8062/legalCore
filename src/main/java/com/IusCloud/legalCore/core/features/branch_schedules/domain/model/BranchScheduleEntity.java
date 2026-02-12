package com.IusCloud.legalCore.core.features.branch_schedules.domain.model;

import com.IusCloud.legalCore.core.base.BaseModel;
import com.IusCloud.legalCore.core.features.branch.domain.model.BranchEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "branch_schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchScheduleEntity extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private BranchEntity branch;

    @Column(name = "day_of_week", nullable = false)
    private Short dayOfWeek; // 1=Monday ... 7=Sunday

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "is_closed", nullable = false)
    private Boolean isClosed = false;
}