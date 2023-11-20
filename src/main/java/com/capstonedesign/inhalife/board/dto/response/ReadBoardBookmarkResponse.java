package com.capstonedesign.inhalife.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadBoardBookmarkResponse {

    private Long boardBookmarkId;
    private Long boardId;
    private String name;
}
