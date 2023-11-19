package com.capstonedesign.inhalife.user.domain;

import com.capstonedesign.inhalife.board.domain.*;
import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.subject.domain.SubjectBookmark;
import com.capstonedesign.inhalife.subject.domain.SubjectReview;
import com.capstonedesign.inhalife.subject.domain.SubjectTaken;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor
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
    private boolean gender;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private List<SubjectTaken> subjectTakenList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<SubjectReview> subjectReviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<SubjectBookmark> subjectBookmarkList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<HobbyOfUser> hobbyList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<RegionOfUser> regionOfUserList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<BoardBookmark> boardBookmarkList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ArticleFavorite> articleFavoriteList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ArticleBookmark> articleBookmarkList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ArticleComplain> articleComplainList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ArticleComment> articleComments = new ArrayList<>();

    public User(String email, String password, Department department, String nickname, int schoolYear, int age, boolean gender) {
        this.email = email;
        this.password = password;
        this.department = department;
        this.nickname = nickname;
        this.schoolYear = schoolYear;
        this.age = age;
        this.gender = gender;
    }
}
