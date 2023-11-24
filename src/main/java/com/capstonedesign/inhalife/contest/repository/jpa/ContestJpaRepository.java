package com.capstonedesign.inhalife.contest.repository.jpa;

import com.capstonedesign.inhalife.contest.domain.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestJpaRepository extends JpaRepository<Contest, Long> {
}
