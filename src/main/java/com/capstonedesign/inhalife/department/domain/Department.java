package com.capstonedesign.inhalife.department.domain;

import com.capstonedesign.inhalife.subject.domain.Professor;
import com.capstonedesign.inhalife.subject.domain.Subject;
import com.capstonedesign.inhalife.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter @Setter
public class Department {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_index")
    private Long id;

    @NotBlank
    @Column(length = 20)
    @Size(max = 20)
    private String name;

    @OneToMany(mappedBy = "department")
    private List<User> users;

    @OneToMany(mappedBy = "department")
    private List<Professor> professors;

    @OneToMany(mappedBy = "department")
    private List<Subject> subjects;
}
