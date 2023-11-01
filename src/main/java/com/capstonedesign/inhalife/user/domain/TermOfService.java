package com.capstonedesign.inhalife.user.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class TermOfService {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_of_service_index")
    private Long id;

    @NotBlank
    @Column(length = 50)
    @Size(max = 50)
    private String title;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String contents;

    @NotBlank
    @Column(length = 20)
    @Size(max = 20)
    private String type;

    @NotNull
    private int version;

    @NotNull
    @ColumnDefault("true")
    @Column(columnDefinition = "TINYINT(1)")
    private boolean required;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public boolean isRequired() {
        return required;
    }
}
