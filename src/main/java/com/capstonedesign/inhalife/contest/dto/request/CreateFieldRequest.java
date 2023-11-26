package com.capstonedesign.inhalife.contest.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateFieldRequest {

    private List<String> fieldNameList;
}
