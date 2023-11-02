package com.capstonedesign.inhalife.subject.domain;

import com.capstonedesign.inhalife.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"user_index", "subject_index"}
                )
        }
)
public class SubjectTaken {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_taken_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "subject_index")
    private Subject subject;

    @NotNull
    @Column(name = "school_year")
    private int schoolYear;

    @NotNull
    private Boolean semester;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
