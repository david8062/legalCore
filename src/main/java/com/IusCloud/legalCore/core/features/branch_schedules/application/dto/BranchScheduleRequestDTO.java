package com.IusCloud.legalCore.core.features.branch_schedules.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchScheduleRequestDTO {

    @NotNull
    private Short dayOfWeek;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
    @NotNull
    private Boolean isClosed;
}