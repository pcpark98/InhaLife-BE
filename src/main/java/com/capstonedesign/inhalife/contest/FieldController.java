package com.capstonedesign.inhalife.contest;

import com.capstonedesign.inhalife.contest.dto.request.CreateFieldRequest;
import com.capstonedesign.inhalife.contest.dto.response.ReadFieldResponse;
import com.capstonedesign.inhalife.contest.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FieldController {

    private final FieldService fieldService;

    @PostMapping("/field")
    public ResponseEntity<Void> createField(
            @RequestBody @Valid CreateFieldRequest request) {

        List<String> fieldList = request.getFieldNameList();
        fieldList.forEach(field -> {
            fieldService.addField(field);
        });

        return ResponseEntity.ok().build();
    }

    @GetMapping("/field")
    public ResponseEntity<List<ReadFieldResponse>> readAllField() {
        List<ReadFieldResponse> fieldList = fieldService.getAll();
        return ResponseEntity.ok(fieldList);
    }
}
