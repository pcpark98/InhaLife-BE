package com.capstonedesign.inhalife.contest.repository.jpa;

import com.capstonedesign.inhalife.contest.domain.FieldOfContest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldOfContestJpaRepository extends JpaRepository<FieldOfContest, Long> {
}
