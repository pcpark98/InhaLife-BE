package com.capstonedesign.inhalife.board.domain;

import com.capstonedesign.inhalife.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"user_index", "article_index"}
                )
        }
)
public class ArticleBookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_bookmark_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "article_index")
    private Article article;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public ArticleBookmark(User user, Article article) {
        this.user = user;
        this.article = article;
    }
}
