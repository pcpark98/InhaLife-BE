package com.capstonedesign.inhalife.contest.repository;

import com.capstonedesign.inhalife.contest.domain.Contest;
import com.capstonedesign.inhalife.contest.repository.jpa.ContestJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ContestRepository {

    private final ContestJpaRepository contestJpaRepository;

    public Long save(Contest contest) {
        return contestJpaRepository.save(contest).getId();
    }

    public Optional<Contest> findById(Long id) {
        Optional<Contest> contest = contestJpaRepository.findById(id);
        return contest;
    }

    public void delete(Long id) {
        contestJpaRepository.deleteById(id);
    }
}
