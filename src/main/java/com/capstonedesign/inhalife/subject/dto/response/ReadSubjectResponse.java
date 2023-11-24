package com.capstonedesign.inhalife.subject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadSubjectResponse {

    private Long subjectId;

    private Long departmentId;
    private String departmentName;

    private Long professorId;
    private String professorName;

    private String subjectName;
    private String subjectType;
    private boolean evaluationType;
    private int schoolYear;
    private boolean semester;
    private boolean isOnline;
}
