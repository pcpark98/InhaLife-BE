package com.capstonedesign.inhalife.subject.repository.jpa;

import com.capstonedesign.inhalife.subject.domain.Subject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectJpaRepository extends JpaRepository<Subject, Long> {

    List<Subject> findAllByDepartment_Id(Long departmentId, Pageable pageable);

    List<Subject> findAllByName(String name, Pageable pageable);

    Optional<Subject> findByNameAndProfessorName(String subjectName, String professorName);
}
