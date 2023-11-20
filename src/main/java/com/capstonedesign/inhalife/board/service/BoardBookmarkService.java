package com.capstonedesign.inhalife.board.service;

import com.capstonedesign.inhalife.board.domain.Board;
import com.capstonedesign.inhalife.board.domain.BoardBookmark;
import com.capstonedesign.inhalife.board.dto.response.ReadBoardBookmarkResponse;
import com.capstonedesign.inhalife.board.dto.response.ReadBoardResponse;
import com.capstonedesign.inhalife.board.exception.DuplicatedBoardBookmarkException;
import com.capstonedesign.inhalife.board.exception.NotExistedBoardBookmarkException;
import com.capstonedesign.inhalife.board.repository.BoardBookmarkRepository;
import com.capstonedesign.inhalife.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardBookmarkService {

    private final BoardBookmarkRepository boardBookmarkRepository;

    @Transactional
    public Long createBoardBookmark(BoardBookmark boardBookmark) {
        Optional<BoardBookmark> duplicatedBoardBookmark = boardBookmarkRepository.findByUserIdAndBoardId(boardBookmark.getUser().getId(), boardBookmark.getBoard().getId());
        if(duplicatedBoardBookmark.isPresent()) throw new DuplicatedBoardBookmarkException();

        return boardBookmarkRepository.save(boardBookmark);
    }

    public BoardBookmark getById(Long id) {
        if(id == null) throw new NotExistedBoardBookmarkException();

        Optional<BoardBookmark> boardBookmark = boardBookmarkRepository.findById(id);
        if(!boardBookmark.isPresent()) throw new NotExistedBoardBookmarkException();

        return boardBookmark.get();
    }

    public List<ReadBoardBookmarkResponse> getByUserId(Long id, int page, int size) {
        List<ReadBoardBookmarkResponse> boardList = new ArrayList<>();

        List<BoardBookmark> boardBookmarkList = boardBookmarkRepository.findByUserId(id, page, size);
        boardBookmarkList.forEach(boardBookmark -> {
            boardList.add(
                    new ReadBoardBookmarkResponse(
                            boardBookmark.getId(),
                            boardBookmark.getBoard().getId(),
                            boardBookmark.getBoard().getName()
                    )
            );
        });

        return boardList;
    }

    public boolean isBookmark(User user, Board board) {
        Optional<BoardBookmark> boardBookmark = boardBookmarkRepository.findByUserIdAndBoardId(user.getId(), board.getId());
        if(boardBookmark.isPresent()) return true;
        else return false;
    }

    @Transactional
    public void deleteBoardBookmark(BoardBookmark boardBookmark) {
        boardBookmarkRepository.delete(boardBookmark.getId());
    }
}
