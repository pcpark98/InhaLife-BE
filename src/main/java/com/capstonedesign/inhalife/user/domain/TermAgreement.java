package com.capstonedesign.inhalife.user.domain;

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
                        columnNames = {"user_index", "term_of_service_index"}
                )
        }
)
public class TermAgreement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_agreement_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "term_of_service_index")
    private TermOfService termOfService;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
