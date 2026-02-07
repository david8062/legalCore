package com.IusCloud.legalCore.core.features.branch.infraestructure.inbound;

import com.IusCloud.legalCore.core.features.branch.application.dto.BranchRequestDTO;
import com.IusCloud.legalCore.core.features.branch.application.dto.BranchResponseDTO;
import com.IusCloud.legalCore.core.features.branch.application.usecase.*;
import com.IusCloud.legalCore.shared.responses.ApiResponse;
import com.IusCloud.legalCore.shared.responses.PagedResponse;
import com.IusCloud.legalCore.shared.responses.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor
public class BranchController {

    private final CreateBranchUseCase createBranchUseCase;
    private final UpdateBranchUseCase updateBranchUseCase;
    private final GetAllBranchesUseCase getAllBranchesUseCase;
    private final GetBranchByIdUseCase getBranchByIdUseCase;
    private final DeleteBranchUseCase deleteBranchUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<BranchResponseDTO>> create(
            @RequestBody @Valid BranchRequestDTO request,
            @RequestHeader("X-Tenant-Id") UUID tenantId
    ) {
        BranchResponseDTO response = createBranchUseCase.execute(request, tenantId);
        return ResponseUtil.created(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BranchResponseDTO>> update(
            @PathVariable UUID id,
            @RequestBody @Valid BranchRequestDTO request,
            @RequestHeader("X-Tenant-Id") UUID tenantId
    ) {
        BranchResponseDTO response = updateBranchUseCase.execute(id, request, tenantId);
        return ResponseUtil.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BranchResponseDTO>> getById(
            @PathVariable UUID id,
            @RequestHeader("X-Tenant-Id") UUID tenantId
    ) {
        BranchResponseDTO response = getBranchByIdUseCase.execute(id, tenantId);
        return ResponseUtil.ok(response);
    }

    @GetMapping
    public ResponseEntity<PagedResponse<BranchResponseDTO>> getAll(
            @RequestHeader("X-Tenant-Id") UUID tenantId,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<BranchResponseDTO> page = getAllBranchesUseCase.execute(tenantId, pageable);
        return ResponseUtil.paged(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id,
            @RequestHeader("X-Tenant-Id") UUID tenantId
    ) {
        deleteBranchUseCase.execute(id, tenantId);
        return ResponseUtil.noContent();
    }
}