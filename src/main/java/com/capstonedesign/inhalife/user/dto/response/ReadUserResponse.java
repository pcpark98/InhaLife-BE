package com.capstonedesign.inhalife.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadUserResponse {

    private Long id;
    private String departmentName;
    private String email;
    private String nickname;
    private int schoolYear;
    private int age;
    private boolean gender;
}
