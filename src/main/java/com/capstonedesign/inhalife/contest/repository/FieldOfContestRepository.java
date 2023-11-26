package com.capstonedesign.inhalife.contest.repository;

import com.capstonedesign.inhalife.contest.domain.FieldOfContest;
import com.capstonedesign.inhalife.contest.repository.jpa.FieldOfContestJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FieldOfContestRepository {

    private final FieldOfContestJpaRepository fieldOfContestJpaRepository;

    public Long save(FieldOfContest fieldOfContest) {
        return fieldOfContestJpaRepository.save(fieldOfContest).getId();
    }

    public Optional<FieldOfContest> findById(Long id) {
        Optional<FieldOfContest> fieldOfContest = fieldOfContestJpaRepository.findById(id);
        return fieldOfContest;
    }

    public void delete(Long id) {
        fieldOfContestJpaRepository.deleteById(id);
    }
}
