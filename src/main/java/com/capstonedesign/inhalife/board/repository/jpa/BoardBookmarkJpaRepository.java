package com.capstonedesign.inhalife.board.repository.jpa;

import com.capstonedesign.inhalife.board.domain.BoardBookmark;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardBookmarkJpaRepository extends JpaRepository<BoardBookmark, Long> {

    List<BoardBookmark> findAllByUser_Id(Long userId, Pageable pageable);

    Optional<BoardBookmark> findByUser_IdAndBoard_Id(Long userId, Long boardId);
}
