package com.capstonedesign.inhalife.board.service;

import com.capstonedesign.inhalife.board.domain.Article;
import com.capstonedesign.inhalife.board.domain.ArticleBookmark;
import com.capstonedesign.inhalife.board.domain.Board;
import com.capstonedesign.inhalife.board.exception.DuplicatedArticleBookmarkException;
import com.capstonedesign.inhalife.board.repository.ArticleBookmarkRepository;
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
public class ArticleBookmarkServiceTest {

    @Autowired ArticleBookmarkService articleBookmarkService;

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleBookmarkRepository articleBookmarkRepository;

    @Test
    public void 게시글_북마크_및_취소() {
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

        // configure Article Bookmark
        ArticleBookmark 게시글_북마크 = new ArticleBookmark(유저1, 게시글);


        // when
        boolean 북마크_이전 = articleBookmarkService.isBookmark(유저1, 게시글);

        articleBookmarkService.createArticleBookmark(게시글_북마크);

        boolean 북마크_이후 = articleBookmarkService.isBookmark(유저1, 게시글);

        articleBookmarkService.deleteArticleBookmark(게시글_북마크);

        boolean 북마크_취소_이후 = articleBookmarkService.isBookmark(유저1, 게시글);


        // then
        assertEquals(false, 북마크_이전);
        assertEquals(true, 북마크_이후);
        assertEquals(false, 북마크_취소_이후);
    }

    @Test(expected = DuplicatedArticleBookmarkException.class)
    public void 이미_북마크한_게시글을_다시_북마크할_수_없다() {
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

        // configure Article Bookmark
        ArticleBookmark 게시글_북마크 = new ArticleBookmark(유저1, 게시글);
        ArticleBookmark 게시글_북마크2 = new ArticleBookmark(유저1, 게시글);
        articleBookmarkRepository.save(게시글_북마크);


        // when
        articleBookmarkService.createArticleBookmark(게시글_북마크2);


        // then
        // DuplicatedArticleBookmarkException 발생
    }

    @Test
    public void 게시글을_북마크한_수_카운트() {
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

        // configure Article Bookmark
        ArticleBookmark 게시글_북마크 = new ArticleBookmark(유저1, 게시글);
        ArticleBookmark 게시글_북마크2 = new ArticleBookmark(유저2, 게시글);
        articleBookmarkRepository.save(게시글_북마크);
        articleBookmarkRepository.save(게시글_북마크2);


        // when
        int 게시글이_북마크된_수 = articleBookmarkService.getArticleBookmarkCountOfArticle(게시글.getId());


        // then
        assertEquals(2, 게시글이_북마크된_수);
    }
}