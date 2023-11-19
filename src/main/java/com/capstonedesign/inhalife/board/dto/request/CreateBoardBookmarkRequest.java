package com.capstonedesign.inhalife.board.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateBoardBookmarkRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long boardId;
}
