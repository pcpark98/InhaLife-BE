package com.capstonedesign.inhalife.user.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SignUpRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private Long departmentId;

    @NotBlank
    private String nickname;

    @NotNull
    private int schoolYear;

    @NotNull
    private int age;

    @NotNull
    private boolean gender;
}
