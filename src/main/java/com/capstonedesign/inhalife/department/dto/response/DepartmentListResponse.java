package com.capstonedesign.inhalife.department.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentListResponse {

    private Long departmentId;
    private String name;
}
