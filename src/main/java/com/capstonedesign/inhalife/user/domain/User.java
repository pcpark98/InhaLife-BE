package com.capstonedesign.inhalife.user.domain;

import com.capstonedesign.inhalife.department.domain.Department;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;

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
    private Integer schoolYear;

    @NotNull
    private Integer age;

    @NotNull
    private Boolean gender;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
