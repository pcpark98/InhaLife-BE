package com.capstonedesign.inhalife.subject.repository.jpa;

import com.capstonedesign.inhalife.subject.domain.SubjectTaken;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectTakenJpaRepository extends JpaRepository<SubjectTaken, Long> {

    public List<SubjectTaken> findAllByUser_Id(Long userId, Pageable pageable);

    public List<SubjectTaken> findAllBySubject_NameAndUser_Id(String subjectName, Long userId);

    public Optional<SubjectTaken> findByUser_IdAndSubject_Id(Long userId, Long subjectId);
}
