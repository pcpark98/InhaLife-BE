package com.capstonedesign.inhalife.subject.domain;

import com.capstonedesign.inhalife.department.domain.Department;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Subject {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "department_index")
    private Department department;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "professor_index")
    private Professor professor;

    @NotBlank
    @Column(length = 30)
    @Size(max = 30)
    private String name;

    @Column(name = "subject_type")
    @Enumerated(EnumType.STRING)
    private SubjectType subjectType;

    @NotNull
    @Column(name = "evaluation_type")
    private Boolean evaluationType;

    @NotNull
    @Column(name = "school_year")
    private int schoolYear;

    @NotNull
    private Boolean semester;

    @NotNull
    @Column(name = "is_online")
    private Boolean isOnline;

    @OneToMany(mappedBy = "subject")
    private List<SubjectTaken> subjectTakenList = new ArrayList<>();

    @OneToMany(mappedBy = "subject")
    private List<SubjectReview> subjectReviewList = new ArrayList<>();

    @OneToMany(mappedBy = "subject")
    private List<SubjectBookmark> subjectBookmarkList = new ArrayList<>();

    public Subject(
            Department department,
            Professor professor,
            String name,
            String subjectType,
            boolean evaluationType,
            int schoolYear,
            boolean semester,
            boolean isOnline) {
        this.department = department;
        this.professor = professor;
        this.name = name;
        this.subjectType = SubjectType.nameOf(subjectType);
        this.evaluationType = evaluationType;
        this.schoolYear = schoolYear;
        this.semester = semester;
        this.isOnline = isOnline;
    }
}
