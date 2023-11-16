package com.capstonedesign.inhalife.user.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Region {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_index")
    private Long id;

    @NotBlank
    @Column(length = 20)
    @Size(max = 20)
    private String name;

    @OneToMany(mappedBy = "region")
    private List<RegionOfUser> userList = new ArrayList<>();

    public Region(String name) {
        this.name = name;
    }

}
