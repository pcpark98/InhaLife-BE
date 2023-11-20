package com.capstonedesign.inhalife.board.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateArticleCommentRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long articleId;

    @NotBlank
    private String contents;
}
