package com.capstonedesign.inhalife.contest.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class GetContestRecommendationRequest {

    @NotNull
    private List<String> fieldNameList;
}
