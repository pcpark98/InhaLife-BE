package com.capstonedesign.inhalife.user.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SetHobbyRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long hobbyId;
}
