package com.capstonedesign.inhalife.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ReadArticleResponse {

    private Long articleId;
    private Long userId;
    private String userNickname;
    private Long boardId;
    private String boardName;
    private String title;
    private String contents;
    private List<String> articleImgUrlList;
    private String createdAt;
    private boolean isWrittenBy;
    private int favoriteCount;
}
