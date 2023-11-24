package com.capstonedesign.inhalife.contest;

import com.capstonedesign.inhalife.contest.dto.response.ReadFieldResponse;
import com.capstonedesign.inhalife.contest.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FieldController {

    private final FieldService fieldService;

    @GetMapping("/field")
    public ResponseEntity<List<ReadFieldResponse>> readAllField() {
        List<ReadFieldResponse> fieldList = fieldService.getAll();
        return ResponseEntity.ok(fieldList);
    }
}
