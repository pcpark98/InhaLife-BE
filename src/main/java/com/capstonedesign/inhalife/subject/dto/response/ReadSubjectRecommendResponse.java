package com.capstonedesign.inhalife.subject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadSubjectRecommendResponse {

    private Long subjectId;
    private String departmentName;
    private String professorName;
    private String subjectName;
    private String subjectType;
    private boolean evaluationType;
    private int schoolYear;
    private boolean semester;
    private boolean isOnline;
}
