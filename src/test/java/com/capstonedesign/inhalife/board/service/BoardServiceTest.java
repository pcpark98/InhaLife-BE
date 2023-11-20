package com.capstonedesign.inhalife.board.service;

import com.capstonedesign.inhalife.board.domain.Board;
import com.capstonedesign.inhalife.board.dto.response.ReadBoardResponse;
import com.capstonedesign.inhalife.board.exception.DuplicatedBoardException;
import com.capstonedesign.inhalife.board.exception.NotExistedBoardException;
import com.capstonedesign.inhalife.board.repository.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTest {

    @Autowired BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void 게시판_생성_및_조회() {
        // given
        // confiugre board
        Board 게시판 = new Board("컴퓨터공학과 게시판");


        // when
        Long 생성한_게시판_아이디 = boardService.createBoard(게시판);
        Board 조회한_게시판 = boardService.getById(게시판.getId());
        List<ReadBoardResponse> 모든_게시판 = boardService.getAll(0, 10);


        // then
        assertEquals(조회한_게시판.getId(), 생성한_게시판_아이디);
        assertEquals(1, 모든_게시판.size());
    }

    @Test(expected = DuplicatedBoardException.class)
    public void 같은_이름의_게시판을_만들수_없다() {
        // given
        // confiugre board
        Board 게시판 = new Board("컴퓨터공학과 게시판");
        Board 게시판2 = new Board("컴퓨터공학과 게시판");
        boardRepository.save(게시판);


        // when
        boardService.createBoard(게시판2);


        // then
        // DuplicatedBoardException 발생
    }

    @Test(expected = NotExistedBoardException.class)
    public void 게시판_삭제() {
        // given
        // confiugre board
        Board 게시판 = new Board("컴퓨터공학과 게시판");
        boardRepository.save(게시판);


        // when
        boardService.deleteBoard(게시판.getId());
        boardService.getById(게시판.getId());


        // then
        // NotExistedBoardException 발생
    }
}