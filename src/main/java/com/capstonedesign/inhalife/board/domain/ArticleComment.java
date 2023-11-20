package com.capstonedesign.inhalife.board.domain;

import com.capstonedesign.inhalife.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Getter @Setter
public class ArticleComment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_comment_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "article_index")
    private Article article;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String contents;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public ArticleComment(User user, Article article, String contents) {
        this.user = user;
        this.article = article;
        this.contents = contents;
    }
}
