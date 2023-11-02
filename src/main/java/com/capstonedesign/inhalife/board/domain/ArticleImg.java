package com.capstonedesign.inhalife.board.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class ArticleImg {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_img_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "article_index")
    private Article article;

    @NotBlank
    @Column(name = "img_url")
    private String imgUrl;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
