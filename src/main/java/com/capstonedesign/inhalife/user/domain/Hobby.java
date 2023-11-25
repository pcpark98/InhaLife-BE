package com.capstonedesign.inhalife.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Hobby {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hobby_index")
    private Long id;

    @NotBlank
    @Column(length = 30)
    @Size(max = 30)
    private String name;

    @NotNull
    private int score;

    @OneToMany(mappedBy = "hobby")
    private List<HobbyOfUser> hobbyOfUserList = new ArrayList<>();

    public Hobby(String name) {
        this.name = name;
    }
}
