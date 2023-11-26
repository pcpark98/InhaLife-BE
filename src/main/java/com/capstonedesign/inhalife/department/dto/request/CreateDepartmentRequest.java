package com.capstonedesign.inhalife.department.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateDepartmentRequest {

    private List<String> departmentNameList;
}
