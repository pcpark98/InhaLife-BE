package com.capstonedesign.inhalife.board.service;

import com.capstonedesign.inhalife.board.domain.Article;
import com.capstonedesign.inhalife.board.domain.ArticleImg;
import com.capstonedesign.inhalife.board.domain.Board;
import com.capstonedesign.inhalife.board.repository.ArticleImgRepository;
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
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ArticleImgServiceTest {

    @Autowired ArticleImgService articleImgService;

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleImgRepository articleImgRepository;

    @Test
    public void 게시글의_모든_이미지_조회() {
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

        // configure Article image
        ArticleImg 이미지1 = new ArticleImg(게시글, "url1");
        ArticleImg 이미지2 = new ArticleImg(게시글, "url2");
        ArticleImg 이미지3 = new ArticleImg(게시글, "url3");
        articleImgRepository.save(이미지1);
        articleImgRepository.save(이미지2);
        articleImgRepository.save(이미지3);


        // when
        List<String> 이미지_리스트 = articleImgService.getAllUrlByArticleId(게시글.getId());
        int 이미지_개수 = 이미지_리스트.size();
        String 첫번째_이미지_url = 이미지_리스트.get(0);


        // then
        assertEquals(3, 이미지_개수);
        assertEquals("url1", 첫번째_이미지_url);
    }
}