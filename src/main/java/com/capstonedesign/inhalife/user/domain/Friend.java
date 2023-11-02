package com.capstonedesign.inhalife.user.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"from_user_index", "to_user_index"}
                )
        }
)
public class Friend {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_index")
    private Long id;

    @Column(name = "from_user_index")
    private Long fromUserIndex;

    @Column(name = "to_user_index")
    private Long toUserIndex;

    @NotNull
    @ColumnDefault("false")
    @Column(columnDefinition = "TINYINT(1)")
    private boolean isFriend;
}
