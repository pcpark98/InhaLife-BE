package com.capstonedesign.inhalife.board.domain;

import com.capstonedesign.inhalife.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"user_index", "article_comment_index"}
                )
        }
)
public class ArticleCommentFavorite {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_comment_favorite_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "article_comment_index")
    private ArticleComment articleComment;
}