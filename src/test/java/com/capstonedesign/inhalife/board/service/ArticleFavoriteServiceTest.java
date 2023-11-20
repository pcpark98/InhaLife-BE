package com.capstonedesign.inhalife.board.service;

import com.capstonedesign.inhalife.board.domain.Article;
import com.capstonedesign.inhalife.board.domain.ArticleFavorite;
import com.capstonedesign.inhalife.board.domain.Board;
import com.capstonedesign.inhalife.board.exception.DuplicatedArticleFavoriteException;
import com.capstonedesign.inhalife.board.repository.ArticleFavoriteRepository;
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
public class ArticleFavoriteServiceTest {

    @Autowired ArticleFavoriteService articleFavoriteService;

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleFavoriteRepository articleFavoriteRepository;

    @Test
    public void 게시글_좋아요_및_취소() {
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

        // configure Article Favorite
        ArticleFavorite 게시글_좋아요 = new ArticleFavorite(유저1, 게시글);


        // when
        boolean 좋아요_이전 = articleFavoriteService.isFavorite(유저1, 게시글);

        articleFavoriteService.createArticleFavorite(게시글_좋아요);

        boolean 좋아요_이후 = articleFavoriteService.isFavorite(유저1, 게시글);

        articleFavoriteService.deleteArticleFavorite(게시글_좋아요);

        boolean 좋아요_취소_이후 = articleFavoriteService.isFavorite(유저1, 게시글);


        // then
        assertEquals(false, 좋아요_이전);
        assertEquals(true, 좋아요_이후);
        assertEquals(false, 좋아요_취소_이후);
    }

    @Test(expected = DuplicatedArticleFavoriteException.class)
    public void 이미_좋아요한_게시글을_다시_좋아요할_수_없다() {
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

        // configure Article Favorite
        ArticleFavorite 게시글_좋아요 = new ArticleFavorite(유저1, 게시글);
        ArticleFavorite 게시글_좋아요2 = new ArticleFavorite(유저1, 게시글);
        articleFavoriteRepository.save(게시글_좋아요);


        // when
        articleFavoriteService.createArticleFavorite(게시글_좋아요2);


        // then
        // DuplicatedArticleFavoriteException 발생
    }

    @Test
    public void 게시글에_달린_좋아요수_카운트() {
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

        // configure Article Favorite
        ArticleFavorite 게시글_좋아요 = new ArticleFavorite(유저1, 게시글);
        ArticleFavorite 게시글_좋아요2 = new ArticleFavorite(유저2, 게시글);
        articleFavoriteRepository.save(게시글_좋아요);
        articleFavoriteRepository.save(게시글_좋아요2);


        // when
        int 게시글에_달린_좋아요수 = articleFavoriteService.getFavoriteCountOfArticle(게시글.getId());


        // then
        assertEquals(2, 게시글에_달린_좋아요수);
    }
}