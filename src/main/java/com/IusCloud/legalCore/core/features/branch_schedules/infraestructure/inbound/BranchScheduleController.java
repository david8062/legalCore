package com.IusCloud.legalCore.core.features.branch_schedules.infraestructure.inbound;

import com.IusCloud.legalCore.core.features.branch_schedules.application.dto.BranchScheduleRequestDTO;
import com.IusCloud.legalCore.core.features.branch_schedules.application.dto.BranchScheduleResponseDTO;
import com.IusCloud.legalCore.core.features.branch_schedules.application.usecase.*;
import com.IusCloud.legalCore.shared.responses.ApiResponse;
import com.IusCloud.legalCore.shared.responses.ListResponse;
import com.IusCloud.legalCore.shared.responses.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/branches/{branchId}/schedules")
@RequiredArgsConstructor
public class BranchScheduleController {

    private final CreateBranchScheduleUseCase createUseCase;
    private final UpdateBranchScheduleUseCase updateUseCase;
    private final DeleteBranchScheduleUseCase deleteUseCase;
    private final GetBranchSchedulesByBranchUseCase getByBranchUseCase;

    // ==========================
    // Create schedule in branch
    // ==========================
    @PostMapping
    public ResponseEntity<ApiResponse<BranchScheduleResponseDTO>> create(
            @PathVariable UUID branchId,
            @RequestBody @Valid BranchScheduleRequestDTO request,
            @RequestHeader("X-Tenant-Id") UUID tenantId
    ) {
        BranchScheduleResponseDTO response = createUseCase.execute(branchId, request, tenantId);
        return ResponseUtil.created(response);
    }

    // ==========================
    // Get all schedules of branch
    // ==========================
    @GetMapping
    public ResponseEntity<ListResponse<BranchScheduleResponseDTO>> getByBranch(
            @PathVariable UUID branchId,
            @RequestHeader("X-Tenant-Id") UUID tenantId
    ) {
        List<BranchScheduleResponseDTO> list = getByBranchUseCase.execute(branchId, tenantId);
        return ResponseUtil.list(list);
    }

    // ==========================
    // Update specific schedule
    // ==========================
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ApiResponse<BranchScheduleResponseDTO>> update(
            @PathVariable UUID branchId,
            @PathVariable UUID scheduleId,
            @RequestBody @Valid BranchScheduleRequestDTO request,
            @RequestHeader("X-Tenant-Id") UUID tenantId
    ) {
        BranchScheduleResponseDTO response =
                updateUseCase.execute(branchId, scheduleId, request, tenantId);
        return ResponseUtil.ok(response);
    }

    // ==========================
    // Delete specific schedule
    // ==========================
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID branchId,
            @PathVariable UUID scheduleId,
            @RequestHeader("X-Tenant-Id") UUID tenantId
    ) {
        deleteUseCase.execute(branchId, scheduleId, tenantId);
        return ResponseUtil.noContent();
    }
}