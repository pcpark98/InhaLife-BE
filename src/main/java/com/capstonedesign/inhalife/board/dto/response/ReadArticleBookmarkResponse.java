package com.capstonedesign.inhalife.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ReadArticleBookmarkResponse {

    private Long articleBookmarkId;
    private Long articleId;
    private Long userId;
    private String userNickname;
    private Long boardId;
    private String boardName;
    private String title;
    private String contents;
    private List<String> articleImgUrlList;
    private LocalDateTime createdAt;
}
