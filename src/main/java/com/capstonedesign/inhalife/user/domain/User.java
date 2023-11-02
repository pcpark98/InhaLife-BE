package com.capstonedesign.inhalife.user.domain;

import com.capstonedesign.inhalife.board.domain.*;
import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.subject.domain.SubjectBookmark;
import com.capstonedesign.inhalife.subject.domain.SubjectReview;
import com.capstonedesign.inhalife.subject.domain.SubjectTaken;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "department_index")
    private Department department;

    @NotBlank
    @Column(length = 40, unique = true)
    @Size(max = 40)
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @Column(length = 30,unique = true)
    @Size(max = 30)
    private String nickname;

    @NotNull
    @Column(name = "school_year")
    private int schoolYear;

    @NotNull
    private int age;

    @NotNull
    private Boolean gender;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private List<SubjectTaken> subjectTakenList;

    @OneToMany(mappedBy = "user")
    private List<SubjectReview> subjectReviewList;

    @OneToMany(mappedBy = "user")
    private List<SubjectBookmark> subjectBookmarkList;

    @OneToMany(mappedBy = "user")
    private List<HobbyOfUser> hobbyList;

    @OneToMany(mappedBy = "user")
    private List<RegionOfUser> regionOfUserList;

    @OneToMany(mappedBy = "user")
    private List<TermAgreement> termAgreementList;

    @OneToMany(mappedBy = "user")
    private List<BoardBookmark> boardBookmarkList;

    @OneToMany(mappedBy = "user")
    private List<Article> articles;

    @OneToMany(mappedBy = "user")
    private List<ArticleFavorite> articleFavoriteList;

    @OneToMany(mappedBy = "user")
    private List<ArticleBookmark> articleBookmarkList;

    @OneToMany(mappedBy = "user")
    private List<ArticleComplain> articleComplainList;
}
