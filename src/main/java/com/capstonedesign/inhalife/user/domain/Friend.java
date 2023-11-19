package com.capstonedesign.inhalife.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
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
    private Boolean isFriend;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Friend(Long fromUserIndex, Long toUserIndex) {
        this.fromUserIndex = fromUserIndex;
        this.toUserIndex = toUserIndex;
        this.isFriend = false;
    }
}
