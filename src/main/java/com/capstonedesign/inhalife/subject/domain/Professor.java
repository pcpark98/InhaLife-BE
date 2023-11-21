package com.capstonedesign.inhalife.subject.domain;

import com.capstonedesign.inhalife.department.domain.Department;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Professor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "department_index")
    private Department department;

    @NotBlank
    @Column(length = 5)
    @Size(max = 5)
    private String name;

    @OneToMany(mappedBy = "professor")
    private List<Subject> subjects = new ArrayList<>();

    public Professor(Department department, String name) {
        this.department = department;
        this.name = name;
    }
}
