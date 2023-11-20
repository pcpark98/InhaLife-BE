package com.capstonedesign.inhalife.board.repository;

import com.capstonedesign.inhalife.board.domain.Board;
import com.capstonedesign.inhalife.board.repository.jpa.BoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final BoardJpaRepository boardJpaRepository;

    public Long save(Board board) {
        return boardJpaRepository.save(board).getId();
    }

    public Optional<Board> findById(Long id) {
        Optional<Board> board = boardJpaRepository.findById(id);
        return board;
    }

    public Optional<Board> findByName(String name) {
        Optional<Board> board = boardJpaRepository.findByName(name);
        return board;
    }

    public List<Board> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return boardJpaRepository.findAll(pageRequest).toList();
    }

    public void delete(Long id) {
        boardJpaRepository.deleteById(id);
    }
}
