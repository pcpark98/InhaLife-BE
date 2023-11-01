package com.capstonedesign.inhalife.user.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"user_index", "region_index"}
                )
        }
)
public class RegionOfUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_of_user_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "region_index")
    private Region region;
}
