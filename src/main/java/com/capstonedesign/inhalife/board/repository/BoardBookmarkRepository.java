package com.capstonedesign.inhalife.board.repository;

import com.capstonedesign.inhalife.board.domain.BoardBookmark;
import com.capstonedesign.inhalife.board.repository.jpa.BoardBookmarkJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardBookmarkRepository {

    private final BoardBookmarkJpaRepository boardBookmarkJpaRepository;

    public Long save(BoardBookmark boardBookmark) {
        return boardBookmarkJpaRepository.save(boardBookmark).getId();
    }

    public Optional<BoardBookmark> findById(Long id) {
        Optional<BoardBookmark> boardBookmark = boardBookmarkJpaRepository.findById(id);
        return boardBookmark;
    }

    public List<BoardBookmark> findByUserId(Long userId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return boardBookmarkJpaRepository.findAllByUser_Id(userId, pageRequest);
    }

    public Optional<BoardBookmark> findByUserIdAndBoardId(Long userId, Long boardId) {
        Optional<BoardBookmark> boardBookmark = boardBookmarkJpaRepository.findByUser_IdAndBoard_Id(userId, boardId);
        return boardBookmark;
    }

    public void delete(Long id) {
        boardBookmarkJpaRepository.deleteById(id);
    }
}
