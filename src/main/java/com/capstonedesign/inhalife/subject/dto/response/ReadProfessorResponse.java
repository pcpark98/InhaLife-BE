package com.capstonedesign.inhalife.subject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadProfessorResponse {

    private Long professorId;
    private Long departmentId;
    private String name;
}
