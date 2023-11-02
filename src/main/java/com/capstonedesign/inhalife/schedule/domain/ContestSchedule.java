package com.capstonedesign.inhalife.schedule.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class ContestSchedule {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contest_schedule_index")
    private Long id;

    @NotBlank
    @Column(length = 100)
    @Size(max = 100)
    private String name;

    @NotBlank
    @Column(length = 100)
    @Size(max = 100)
    private String schedule;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @Column(name = "information_url")
    private String informationUrl;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
