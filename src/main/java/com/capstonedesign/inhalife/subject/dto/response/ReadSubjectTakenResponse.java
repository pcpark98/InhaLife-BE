package com.capstonedesign.inhalife.subject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadSubjectTakenResponse {

    private Long subjectTakenId;

    private Long subjectId;
    private String subjectName;

    private Long professorId;
    private String professorName;

    private int schoolYear;
    private boolean semester;
}
