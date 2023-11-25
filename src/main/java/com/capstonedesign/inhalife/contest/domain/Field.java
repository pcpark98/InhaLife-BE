package com.capstonedesign.inhalife.contest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Field {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_index")
    private Long id;

    @NotBlank
    @Column(length = 30)
    @Size(max = 30)
    private String name;

    @OneToMany(mappedBy = "field")
    private List<FieldOfContest> fieldOfContestList = new ArrayList<>();

    public Field(String name) {
        this.name = name;
    }
}
