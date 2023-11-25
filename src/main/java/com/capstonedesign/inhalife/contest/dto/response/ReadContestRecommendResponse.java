package com.capstonedesign.inhalife.contest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ReadContestRecommendResponse {

    private Long contestId;
    private String contestName;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String informationUrl;
    private List<String> fieldNameList;
}
