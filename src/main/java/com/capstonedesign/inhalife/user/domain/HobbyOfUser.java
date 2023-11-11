package com.capstonedesign.inhalife.user.domain;

import com.capstonedesign.inhalife.subject.domain.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"user_index", "hobby_index"}
                )
        }
)
public class HobbyOfUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hobby_of_user_index")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_index")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hobby_index")
    private Hobby hobby;

    public HobbyOfUser(User user, Hobby hobby) {
        this.user = user;
        this.hobby = hobby;
    }
}
