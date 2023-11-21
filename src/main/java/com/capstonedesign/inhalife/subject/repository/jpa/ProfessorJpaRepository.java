package com.capstonedesign.inhalife.subject.repository.jpa;

import com.capstonedesign.inhalife.subject.domain.Professor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorJpaRepository extends JpaRepository<Professor, Long> {

    List<Professor> findAllByDepartment_Id(Long departmentId, Pageable pageable);
}
