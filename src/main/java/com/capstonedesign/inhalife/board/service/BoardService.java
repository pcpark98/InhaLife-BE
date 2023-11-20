package com.capstonedesign.inhalife.board.service;

import com.capstonedesign.inhalife.board.domain.Board;
import com.capstonedesign.inhalife.board.dto.response.ReadBoardResponse;
import com.capstonedesign.inhalife.board.exception.DuplicatedBoardException;
import com.capstonedesign.inhalife.board.exception.NotExistedBoardException;
import com.capstonedesign.inhalife.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long createBoard(Board board) {
        Optional<Board> duplicatedBoard = boardRepository.findByName(board.getName());
        if(duplicatedBoard.isPresent()) throw new DuplicatedBoardException();

        return boardRepository.save(board);
    }

    public Board getById(Long id) {
        if(id == null) throw new NotExistedBoardException();

        Optional<Board> board = boardRepository.findById(id);
        if(!board.isPresent()) throw new NotExistedBoardException();

        return board.get();
    }

    public List<ReadBoardResponse> getAll(int page, int size) {
        List<ReadBoardResponse> responseList = new ArrayList<>();

        List<Board> boardList = boardRepository.findAll(page, size);
        boardList.forEach(board -> {
            responseList.add(
                    new ReadBoardResponse(
                            board.getId(),
                            board.getName()
                    )
            );
        });

        return responseList;
    }

    @Transactional
    public void deleteBoard(Long id) {
        if(id == null) throw new NotExistedBoardException();

        Optional<Board> board = boardRepository.findById(id);
        if(!board.isPresent()) throw new NotExistedBoardException();

        boardRepository.delete(id);
    }
}
