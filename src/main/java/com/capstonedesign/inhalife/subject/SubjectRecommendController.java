package com.capstonedesign.inhalife.subject;

import com.capstonedesign.inhalife.subject.dto.request.GetSubjectRecommendationRequest;
import com.capstonedesign.inhalife.subject.dto.response.ReadSubjectRecommendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubjectRecommendController {

    // TODO: 과목 추천
    /*
    @PostMapping("/subject-recommend")
    public ResponseEntity<List<ReadSubjectRecommendResponse>> getSubjectRecommendation(
            @RequestBody @Valid GetSubjectRecommendationRequest[] requestList) {
        for(GetSubjectRecommendationRequest request : requestList) {

        }
    }

     */
}
