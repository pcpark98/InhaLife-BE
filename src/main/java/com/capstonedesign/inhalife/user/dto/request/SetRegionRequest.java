package com.capstonedesign.inhalife.user.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SetRegionRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long regionId;
}
