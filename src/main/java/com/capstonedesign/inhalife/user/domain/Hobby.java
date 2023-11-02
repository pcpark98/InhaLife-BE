package com.capstonedesign.inhalife.user.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter @Setter
public class Hobby {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hobby_index")
    private Long id;

    @NotBlank
    @Column(length = 30)
    @Size(max = 30)
    private String name;

    @OneToMany(mappedBy = "hobby")
    private List<HobbyOfUser> hobbyOfUserList;
}
