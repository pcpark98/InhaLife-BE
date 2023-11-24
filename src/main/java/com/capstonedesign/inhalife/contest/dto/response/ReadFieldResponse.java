package com.capstonedesign.inhalife.contest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadFieldResponse {

    private Long fieldId;
    private String fieldName;
}
