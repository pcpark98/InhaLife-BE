package com.capstonedesign.inhalife.subject;

import com.capstonedesign.inhalife.subject.dto.response.ReadProfessorResponse;
import com.capstonedesign.inhalife.subject.service.ProfessorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "교수 API", description = "교수 관련 API")
@RestController
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping("/professor")
    public ResponseEntity<List<ReadProfessorResponse>> readAllProfessor(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<ReadProfessorResponse> professorList = professorService.getAll(page, size);
        return ResponseEntity.ok(professorList);
    }

    @GetMapping("/department/{departmentIndex}/professor")
    public ResponseEntity<List<ReadProfessorResponse>> readAllProfessorByDepartmentId(
            @PathVariable Long departmentIndex,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<ReadProfessorResponse> professorList = professorService.getAllByDepartmentId(departmentIndex, page, size);
        return ResponseEntity.ok(professorList);
    }
}
