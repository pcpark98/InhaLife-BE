package com.capstonedesign.inhalife.department;

import com.capstonedesign.inhalife.department.dto.response.DepartmentListResponse;
import com.capstonedesign.inhalife.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

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
}
