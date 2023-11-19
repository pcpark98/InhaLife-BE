package com.capstonedesign.inhalife.board.service;

import com.capstonedesign.inhalife.board.domain.Board;
import com.capstonedesign.inhalife.board.domain.BoardBookmark;
import com.capstonedesign.inhalife.board.dto.response.ReadBoardResponse;
import com.capstonedesign.inhalife.board.exception.DuplicatedBoardBookmarkException;
import com.capstonedesign.inhalife.board.exception.NotExistedBoardBookmarkException;
import com.capstonedesign.inhalife.board.repository.BoardBookmarkRepository;
import com.capstonedesign.inhalife.board.repository.BoardRepository;
import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.department.repository.DepartmentRepository;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.repository.UserRepository;
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
public class BoardBookmarkServiceTest {

    @Autowired BoardBookmarkService boardBookmarkService;

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    BoardBookmarkRepository boardBookmarkRepository;

    @Test
    public void 게시판_즐겨찾기_및_조회() {
        // given
        // configure department
        Department 학과 = new Department("컴퓨터공학과");
        departmentRepository.save(학과);

        // configure user info
        User 유저1 = new User(
                "email@email.com",
                "password",
                학과,
                "nickname",
                1,
                20,
                true
        );
        User 유저2 = new User(
                "email2@email.com",
                "password2",
                학과,
                "nickname2",
                1,
                20,
                true
        );
        userRepository.save(유저1);
        userRepository.save(유저2);

        // confiugre board
        Board 게시판 = new Board("컴퓨터공학과 게시판");
        Board 게시판2 = new Board("정보통신공학과 게시판");
        boardRepository.save(게시판);
        boardRepository.save(게시판2);

        // configure board bookmark
        BoardBookmark 게시판_즐겨찾기 = new BoardBookmark(유저1, 게시판);
        BoardBookmark 게시판_즐겨찾기2 = new BoardBookmark(유저1, 게시판2);
        BoardBookmark 게시판_즐겨찾기3 = new BoardBookmark(유저2, 게시판);


        // when
        boardBookmarkService.createBoardBookmark(게시판_즐겨찾기);
        boardBookmarkService.createBoardBookmark(게시판_즐겨찾기2);
        boardBookmarkService.createBoardBookmark(게시판_즐겨찾기3);

        BoardBookmark 게시판_즐겨찾기_조회 = boardBookmarkService.getById(게시판_즐겨찾기.getId());
        List<ReadBoardResponse> 유저1이_즐겨찾기한_게시판 = boardBookmarkService.getByUserId(유저1.getId(), 0, 10);
        List<ReadBoardResponse> 유저2가_즐겨찾기한_게시판 = boardBookmarkService.getByUserId(유저2.getId(), 0, 10);
        boolean 유저1이_게시판을_좋아요했는가 = boardBookmarkService.isBookmark(유저1, 게시판);
        boolean 유저1이_게시판2를_좋아요했는가 = boardBookmarkService.isBookmark(유저1, 게시판2);
        boolean 유저2가_게시판을_좋아요했는가 = boardBookmarkService.isBookmark(유저2, 게시판);
        boolean 유저2가_게시판2를_좋아요했는가 = boardBookmarkService.isBookmark(유저2, 게시판2);


        // then
        assertEquals(게시판_즐겨찾기, 게시판_즐겨찾기_조회);
        assertEquals(2, 유저1이_즐겨찾기한_게시판.size());
        assertEquals(1, 유저2가_즐겨찾기한_게시판.size());
        assertEquals(true, 유저1이_게시판을_좋아요했는가);
        assertEquals(true, 유저1이_게시판2를_좋아요했는가);
        assertEquals(true, 유저2가_게시판을_좋아요했는가);
        assertEquals(false, 유저2가_게시판2를_좋아요했는가);
    }

    @Test(expected = DuplicatedBoardBookmarkException.class)
    public void 같은_게시판을_여러번_북마크할_수_없다() {
        // given
        // configure department
        Department 학과 = new Department("컴퓨터공학과");
        departmentRepository.save(학과);

        // configure user info
        User 유저1 = new User(
                "email@email.com",
                "password",
                학과,
                "nickname",
                1,
                20,
                true
        );
        userRepository.save(유저1);

        // confiugre board
        Board 게시판 = new Board("컴퓨터공학과 게시판");
        boardRepository.save(게시판);

        // configure board bookmark
        BoardBookmark 게시판_즐겨찾기 = new BoardBookmark(유저1, 게시판);
        BoardBookmark 게시판_즐겨찾기2 = new BoardBookmark(유저1, 게시판);
        boardBookmarkRepository.save(게시판_즐겨찾기);


        // when
        boardBookmarkService.createBoardBookmark(게시판_즐겨찾기2);


        // then
        // DuplicatedBoardBookmarkException 발생
    }

    @Test(expected = NotExistedBoardBookmarkException.class)
    public void 존재하지_않는_즐겨찾기_조회() {
        // given


        // when
        boardBookmarkService.getById(1L);


        // then
        // NotExistedBoardBookmarkException 발생
    }

    @Test
    public void 게시판_즐겨찾기_취소() {
        // given
        // configure department
        Department 학과 = new Department("컴퓨터공학과");
        departmentRepository.save(학과);

        // configure user info
        User 유저1 = new User(
                "email@email.com",
                "password",
                학과,
                "nickname",
                1,
                20,
                true
        );
        userRepository.save(유저1);

        // confiugre board
        Board 게시판 = new Board("컴퓨터공학과 게시판");
        boardRepository.save(게시판);

        // configure board bookmark
        BoardBookmark 게시판_즐겨찾기 = new BoardBookmark(유저1, 게시판);
        boardBookmarkRepository.save(게시판_즐겨찾기);


        // when
        boolean 삭제_이전_즐겨찾기_여부 = boardBookmarkService.isBookmark(유저1, 게시판);

        boardBookmarkService.deleteBoardBookmark(게시판_즐겨찾기);

        boolean 삭제_이후_즐겨찾기_여부 = boardBookmarkService.isBookmark(유저1, 게시판);


        // then
        assertEquals(true, 삭제_이전_즐겨찾기_여부);
        assertEquals(false, 삭제_이후_즐겨찾기_여부);
    }
}