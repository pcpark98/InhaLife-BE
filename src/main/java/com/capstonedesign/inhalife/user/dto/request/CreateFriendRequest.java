package com.capstonedesign.inhalife.user.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateFriendRequest {

    @NotNull
    Long fromUserId;

    @NotNull
    Long toUserId;

    @NotNull
    boolean type;
    // false : request, true : accept
}
