package com.capstonedesign.inhalife.contest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"contest_index", "field_index"}
                )
        }
)
public class FieldOfContest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_of_contest_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "contest_index")
    private Contest contest;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "field_index")
    private Field field;
}
