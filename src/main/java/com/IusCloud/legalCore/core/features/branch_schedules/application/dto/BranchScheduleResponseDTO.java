package com.IusCloud.legalCore.core.features.branch_schedules.application.dto;

import com.IusCloud.legalCore.core.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class BranchScheduleResponseDTO extends BaseDTO {

    private UUID branchId;
    private Short dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean isClosed;
}