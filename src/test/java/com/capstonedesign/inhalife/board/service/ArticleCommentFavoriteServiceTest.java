package com.capstonedesign.inhalife.board.service;

import com.capstonedesign.inhalife.board.domain.Article;
import com.capstonedesign.inhalife.board.domain.ArticleComment;
import com.capstonedesign.inhalife.board.domain.ArticleCommentFavorite;
import com.capstonedesign.inhalife.board.domain.Board;
import com.capstonedesign.inhalife.board.exception.DuplicatedArticleCommentFavoriteException;
import com.capstonedesign.inhalife.board.repository.ArticleCommentFavoriteRepository;
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
public class ArticleCommentFavoriteServiceTest {

    @Autowired ArticleCommentFavoriteService articleCommentFavoriteService;

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
    @Autowired
    ArticleCommentFavoriteRepository articleCommentFavoriteRepository;

    @Test
    public void 댓글_좋아요_및_취소() {
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

        // configure Article Comment Favorite
        ArticleCommentFavorite 댓글_좋아요 = new ArticleCommentFavorite(유저1, 댓글);


        // when
        boolean 좋아요_이전 = articleCommentFavoriteService.isFavorite(유저1, 댓글);

        articleCommentFavoriteService.createArticleCommentFavorite(댓글_좋아요);

        boolean 좋아요_이후 = articleCommentFavoriteService.isFavorite(유저1, 댓글);

        articleCommentFavoriteService.deleteArticleCommentFavorite(댓글_좋아요);

        boolean 좋아요_취소_이후 = articleCommentFavoriteService.isFavorite(유저1, 댓글);


        // then
        assertEquals(false, 좋아요_이전);
        assertEquals(true, 좋아요_이후);
        assertEquals(false, 좋아요_취소_이후);
    }

    @Test(expected = DuplicatedArticleCommentFavoriteException.class)
    public void 이미_좋아요한_댓글을_다시_좋아요할_수_없다() {
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

        // configure Article Comment
        ArticleComment 댓글 = new ArticleComment(유저1, 게시글, "내용");
        articleCommentRepository.save(댓글);

        // configure Article Comment Favorite
        ArticleCommentFavorite 댓글_좋아요 = new ArticleCommentFavorite(유저1, 댓글);
        ArticleCommentFavorite 댓글_좋아요2 = new ArticleCommentFavorite(유저1, 댓글);
        articleCommentFavoriteRepository.save(댓글_좋아요);


        // when
        articleCommentFavoriteService.createArticleCommentFavorite(댓글_좋아요2);


        // then
        // DuplicatedArticleCommentFavoriteException 발생
    }

    @Test
    public void 댓글에_달린_좋아요수_카운트() {
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

        // configure Article Comment
        ArticleComment 댓글 = new ArticleComment(유저1, 게시글, "내용");
        articleCommentRepository.save(댓글);

        // configure Article Comment Favorite
        ArticleCommentFavorite 댓글_좋아요 = new ArticleCommentFavorite(유저1, 댓글);
        ArticleCommentFavorite 댓글_좋아요2 = new ArticleCommentFavorite(유저2, 댓글);
        articleCommentFavoriteRepository.save(댓글_좋아요);
        articleCommentFavoriteRepository.save(댓글_좋아요2);


        // when
        int 댓글에_달린_좋아요_수 = articleCommentFavoriteService.getFavoriteCountOfArticleComment(댓글.getId());


        // then
        assertEquals(2, 댓글에_달린_좋아요_수);
    }
}