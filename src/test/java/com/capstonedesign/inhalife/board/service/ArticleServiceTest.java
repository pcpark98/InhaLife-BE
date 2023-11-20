package com.capstonedesign.inhalife.board.service;

import com.capstonedesign.inhalife.board.domain.Article;
import com.capstonedesign.inhalife.board.domain.Board;
import com.capstonedesign.inhalife.board.exception.NotExistedArticleException;
import com.capstonedesign.inhalife.board.repository.ArticleRepository;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ArticleServiceTest {

    @Autowired ArticleService articleService;

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    ArticleRepository articleRepository;

    @Test
    public void 게시글_작성() {
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

        // configure Article
        Article 게시글 = new Article(유저1, 게시판, "제목", "내용");
        List<MultipartFile> 이미지 = new ArrayList<>();

        // when
        articleService.createArticle(게시글, 이미지);
        Article 조회한_게시글 = articleService.getById(게시글.getId());


        // then
        assertEquals(게시글, 조회한_게시글);
    }

    @Test(expected = NotExistedArticleException.class)
    public void 존재하지_않는_게시글을_조회할_수_없다() {
        // given


        // when
        articleService.getById(1L);

        // then
        // NotExistedArticleException 발생
    }

    @Test
    public void 유저가_작성한_모든_게시글_조회() {
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
        boardRepository.save(게시판);

        // configure Article
        Article 게시글 = new Article(유저1, 게시판, "제목", "내용");
        Article 게시글2 = new Article(유저1, 게시판, "제목", "내용");
        Article 게시글3 = new Article(유저2, 게시판, "제목", "내용");
        articleRepository.save(게시글);
        articleRepository.save(게시글2);
        articleRepository.save(게시글3);


        // when
        int 유저1의_게시글_수 = articleService.getArticleByUserId(유저1.getId(), 0, 10).size();
        int 유저2의_게시글_수 = articleService.getArticleByUserId(유저2.getId(), 0, 10).size();


        // then
        assertEquals(2, 유저1의_게시글_수);
        assertEquals(1, 유저2의_게시글_수);
    }

    @Test
    public void 게시판의_모든_게시글_조회() {
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
        Board 게시판2 = new Board("정보통신공학과 게시판");
        boardRepository.save(게시판);
        boardRepository.save(게시판2);

        // configure Article
        Article 게시글 = new Article(유저1, 게시판, "제목", "내용");
        Article 게시글2 = new Article(유저1, 게시판, "제목", "내용");
        Article 게시글3 = new Article(유저1, 게시판2, "제목", "내용");
        articleRepository.save(게시글);
        articleRepository.save(게시글2);
        articleRepository.save(게시글3);


        // when
        int 게시판1의_게시글_수 = articleService.getArticleByBoardId(게시판.getId(), 0, 10).size();
        int 게시판2의_게시글_수 = articleService.getArticleByBoardId(게시판2.getId(), 0, 10).size();


        // then
        assertEquals(2, 게시판1의_게시글_수);
        assertEquals(1, 게시판2의_게시글_수);
    }

    @Test
    public void 작성여부_확인() {
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
        boardRepository.save(게시판);

        // configure Article
        Article 게시글 = new Article(유저1, 게시판, "제목", "내용");
        articleRepository.save(게시글);


        // when
        boolean 유저1이_썼는가 = articleService.isWrittenBy(유저1, 게시글);
        boolean 유저2가_썼는가 = articleService.isWrittenBy(유저2, 게시글);


        // then
        assertEquals(true, 유저1이_썼는가);
        assertEquals(false, 유저2가_썼는가);
    }

    @Test(expected = NotExistedArticleException.class)
    public void 게시글_삭제() {
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

        // configure Article
        Article 게시글 = new Article(유저1, 게시판, "제목", "내용");
        articleRepository.save(게시글);


        // when
        articleService.deleteArticle(게시글);
        articleService.getById(게시글.getId());


        // then
        // NotExistedArticleException 발생
    }
}