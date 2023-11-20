package com.capstonedesign.inhalife.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReadArticleCommentResponse {

    private Long articleCommentId;
    private Long userId;
    private Long articleId;
    private String contents;
    private LocalDateTime createdAt;
}
