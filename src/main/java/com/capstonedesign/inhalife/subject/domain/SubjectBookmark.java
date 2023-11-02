package com.capstonedesign.inhalife.subject.domain;

import com.capstonedesign.inhalife.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"user_index", "subject_index"}
                )
        }
)
public class SubjectBookmark {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_bookmark_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "subject_index")
    private Subject subject;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
