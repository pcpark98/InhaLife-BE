package com.capstonedesign.inhalife.subject;

import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.department.service.DepartmentService;
import com.capstonedesign.inhalife.subject.dto.response.ReadSubjectResponse;
import com.capstonedesign.inhalife.subject.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "과목 API", description = "과목 관련 API")
@RestController
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;
    private final DepartmentService departmentService;

    @Operation(summary = "모든 과목 조회 API", description = "모든 과목을 조회합니다.")
    @Parameters({
            @Parameter(name = "page", description = "조회할 과목이 위치한 페이지" ),
            @Parameter(name = "size", description = "한 페이지에 조회할 과목의 개수" )
    })
    @GetMapping("/all-subject")
    public ResponseEntity<List<ReadSubjectResponse>> readAllSubject(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<ReadSubjectResponse> subjectList = subjectService.getAll(page, size);

        return ResponseEntity.ok(subjectList);
    }

    @Operation(summary = "학과의 모든 과목 조회 API", description = "특정 학과의 모든 과목을 조회합니다.")
    @Parameters({
            @Parameter(name = "departmentIndex", description = "조회할 학과의 id"),
            @Parameter(name = "page", description = "조회할 과목이 위치한 페이지"),
            @Parameter(name = "size", description = "한 페이지에 조회할 과목의 개수")
    })
    @GetMapping("/department/{departmentIndex}/subject")
    public ResponseEntity<List<ReadSubjectResponse>> readAllSubjectByDepartmentId(
            @PathVariable Long departmentIndex,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Department department = departmentService.getById(departmentIndex);

        List<ReadSubjectResponse> subjectList = subjectService.getAllByDepartmentId(department.getId(), page, size);

        return ResponseEntity.ok(subjectList);
    }

    @Operation(summary = "과목명으로 과목 조회 API", description = "과목명으로 모든 과목을 조회합니다.")
    @Parameters({
            @Parameter(name = "name", description = "조회할 과목의 이름"),
            @Parameter(name = "page", description = "조회할 과목이 위치한 페이지"),
            @Parameter(name = "size", description = "한 페이지에 조회할 과목의 개수")
    })
    @GetMapping("/subject")
    public ResponseEntity<List<ReadSubjectResponse>> readAllSubjectByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<ReadSubjectResponse> subjectList = subjectService.getAllByName(name, page, size);

        return ResponseEntity.ok(subjectList);
    }
}
