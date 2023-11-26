package com.capstonedesign.inhalife.department;

import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.department.dto.request.CreateDepartmentRequest;
import com.capstonedesign.inhalife.department.dto.response.DepartmentListResponse;
import com.capstonedesign.inhalife.department.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "학과 API", description = "학과 관련 API")
@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(summary = "학과 목록 조회 API", description = "저장된 모든 학과 리스트를 조회합니다.")
    @GetMapping
    public ResponseEntity<List<DepartmentListResponse>> getAllDepartmentList() {
        List<DepartmentListResponse> departmentList = departmentService.getAll().stream()
                .map(d -> {
                            return new DepartmentListResponse(
                                    d.getId(),
                                    d.getName());
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(departmentList);
    }

    @PostMapping
    public ResponseEntity<Void> createDepartment(
            @RequestBody @Valid CreateDepartmentRequest request) {

        List<String> departmentList = request.getDepartmentNameList();
        departmentList.forEach(department -> {
            departmentService.addDepartment(department);
        });

        return ResponseEntity.ok().build();
    }
}
