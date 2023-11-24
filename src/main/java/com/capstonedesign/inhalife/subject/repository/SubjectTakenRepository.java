package com.capstonedesign.inhalife.subject.repository;

import com.capstonedesign.inhalife.subject.domain.SubjectTaken;
import com.capstonedesign.inhalife.subject.repository.jpa.SubjectTakenJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SubjectTakenRepository {

    private final SubjectTakenJpaRepository subjectTakenJpaRepository;

    public Long save(SubjectTaken subjectTaken) {
        return subjectTakenJpaRepository.save(subjectTaken).getId();
    }

    public Optional<SubjectTaken> findById(Long id) {
        Optional<SubjectTaken> subjectTaken = subjectTakenJpaRepository.findById(id);
        return subjectTaken;
    }

    public List<SubjectTaken> findAllByUserIndex(Long userId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return subjectTakenJpaRepository.findAllByUser_Id(userId, pageRequest);
    }

    public List<SubjectTaken> findAllBySubjectNameAndUserIndex(String subjectName, Long userId) {
        return subjectTakenJpaRepository.findAllBySubject_NameAndUser_Id(subjectName, userId);
    }

    public Optional<SubjectTaken> findByUserIdAndSubjectId(Long userId, Long subjectId) {
        Optional<SubjectTaken> subjectTaken = subjectTakenJpaRepository.findByUser_IdAndSubject_Id(userId, subjectId);
        return subjectTaken;
    }
}
