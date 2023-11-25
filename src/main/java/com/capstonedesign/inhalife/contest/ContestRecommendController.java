package com.capstonedesign.inhalife.contest;

import com.capstonedesign.inhalife.contest.domain.Field;
import com.capstonedesign.inhalife.contest.dto.request.GetContestRecommendationRequest;
import com.capstonedesign.inhalife.contest.dto.response.ReadContestRecommendResponse;
import com.capstonedesign.inhalife.contest.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContestRecommendController {

    private final FieldService fieldService;

    // TODO: 대회 추천은 프론트에서 필드 받아오는거까지 작업 되었음.
    /*
    @PostMapping("/contest-recommend")
    public ResponseEntity<ReadContestRecommendResponse> getContestRecommendation(
            @RequestBody @Valid GetContestRecommendationRequest request) {
        List<Field> selectedField = new ArrayList<>();

        List<String> selectedFieldName = request.getFieldNameList();
        selectedFieldName.forEach(fieldName -> {
            Field field = fieldService.getByName(fieldName);
            selectedField.add(field);
        });
    }
     */
}
