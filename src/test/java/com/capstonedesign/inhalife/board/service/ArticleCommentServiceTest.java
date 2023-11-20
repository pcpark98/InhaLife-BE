package com.capstonedesign.inhalife.board.service;

import com.capstonedesign.inhalife.board.domain.Article;
import com.capstonedesign.inhalife.board.domain.ArticleComment;
import com.capstonedesign.inhalife.board.domain.Board;
import com.capstonedesign.inhalife.board.exception.NotExistedArticleCommentException;
import com.capstonedesign.inhalife.board.repository.ArticleCommentRepository;
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
public class ArticleCommentServiceTest {

    @Autowired ArticleCommentService articleCommentService;

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleCommentRepository articleCommentRepository;

    @Test
    public void 게시글_댓글_작성() {
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
        articleRepository.save(게시글);

        // configure Article Comment
        ArticleComment 댓글 = new ArticleComment(유저1, 게시글, "내용");


        // when
        articleCommentService.createArticleComment(댓글);
        ArticleComment 조회한_댓글 = articleCommentService.getById(댓글.getId());


        // then
        assertEquals(댓글, 조회한_댓글);
    }

    @Test
    public void 유저가_작성한_모든_댓글_조회() {
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
        List<MultipartFile> 이미지 = new ArrayList<>();
        articleRepository.save(게시글);

        // configure Article Comment
        ArticleComment 댓글 = new ArticleComment(유저1, 게시글, "내용");
        ArticleComment 댓글2 = new ArticleComment(유저1, 게시글, "내용");
        ArticleComment 댓글3 = new ArticleComment(유저2, 게시글, "내용");
        articleCommentRepository.save(댓글);
        articleCommentRepository.save(댓글2);
        articleCommentRepository.save(댓글3);


        // when
        int 유저1이_작성한_댓글_수 = articleCommentService.getAllArticleCommentByUserId(유저1.getId(), 0, 10).size();
        int 유저2가_작성한_댓글_수 = articleCommentService.getAllArticleCommentByUserId(유저2.getId(), 0, 10).size();


        // then
        assertEquals(2, 유저1이_작성한_댓글_수);
        assertEquals(1, 유저2가_작성한_댓글_수);
    }

    @Test
    public void 게시글에_달린_모든_댓글_조회() {
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
        Article 게시글2 = new Article(유저1, 게시판, "제목", "내용");
        articleRepository.save(게시글);
        articleRepository.save(게시글2);

        // configure Article Comment
        ArticleComment 댓글 = new ArticleComment(유저1, 게시글, "내용");
        ArticleComment 댓글2 = new ArticleComment(유저1, 게시글, "내용");
        ArticleComment 댓글3 = new ArticleComment(유저1, 게시글2, "내용");
        articleCommentRepository.save(댓글);
        articleCommentRepository.save(댓글2);
        articleCommentRepository.save(댓글3);


        // when
        int 게시글에_달린_댓글수 = articleCommentService.getAllArticleCommentByArticleId(게시글.getId(), 0, 10).size();
        int 게시글2에_달린_댓글수 = articleCommentService.getAllArticleCommentByArticleId(게시글2.getId(), 0, 10).size();


        // then
        assertEquals(2, 게시글에_달린_댓글수);
        assertEquals(1, 게시글2에_달린_댓글수);
    }

    @Test(expected = NotExistedArticleCommentException.class)
    public void 게시글_댓글_삭제() {
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
        articleRepository.save(게시글);

        // configure Article Comment
        ArticleComment 댓글 = new ArticleComment(유저1, 게시글, "내용");
        articleCommentRepository.save(댓글);


        // when
        articleCommentService.deleteArticleComment(댓글);
        articleCommentService.getById(댓글.getId());


        // then
        // NotExistedArticleCommentException 발생
    }
}