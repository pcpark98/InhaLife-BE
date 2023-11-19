package com.capstonedesign.inhalife.user.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestFriendRequest {

    @NotNull
    Long fromUserId;

    @NotNull
    Long toUserId;
}
