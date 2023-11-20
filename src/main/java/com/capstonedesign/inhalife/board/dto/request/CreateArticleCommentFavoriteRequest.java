package com.capstonedesign.inhalife.board.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateArticleCommentFavoriteRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long articleCommentId;
}
