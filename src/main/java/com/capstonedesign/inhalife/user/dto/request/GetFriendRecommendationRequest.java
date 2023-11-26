package com.capstonedesign.inhalife.user.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class GetFriendRecommendationRequest {

    @NotNull
    private List<String> sprots;

    @NotNull
    private List<String> hobbies;

    @NotBlank
    private String department;

    @NotNull
    private int age;

    @NotBlank
    private String gender;
}
