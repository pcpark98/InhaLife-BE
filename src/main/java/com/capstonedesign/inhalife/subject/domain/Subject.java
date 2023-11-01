package com.capstonedesign.inhalife.subject.domain;

import com.capstonedesign.inhalife.department.domain.Department;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    private Integer schoolYear;

    @NotNull
    private Boolean semester;

    @NotNull
    @Column(name = "is_online")
    private Boolean isOnline;

    @OneToMany(mappedBy = "subject")
    private List<SubjectTaken> subjectTakenList;

    @OneToMany(mappedBy = "subject")
    private List<SubjectReview> subjectReviewList;
}
