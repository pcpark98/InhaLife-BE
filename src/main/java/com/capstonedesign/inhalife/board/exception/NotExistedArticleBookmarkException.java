package com.capstonedesign.inhalife.board.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedArticleBookmarkException extends ServiceException {

    public NotExistedArticleBookmarkException(){
        super(HttpStatus.NOT_FOUND, "북마크하지 않은 게시글입니다.");
    }
}
