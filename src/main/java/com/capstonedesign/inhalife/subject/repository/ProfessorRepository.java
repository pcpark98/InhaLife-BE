package com.capstonedesign.inhalife.subject.repository;

import com.capstonedesign.inhalife.subject.domain.Professor;
import com.capstonedesign.inhalife.subject.repository.jpa.ProfessorJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProfessorRepository {

    private final ProfessorJpaRepository professorJpaRepository;

    public Long save(Professor professor) {
        return professorJpaRepository.save(professor).getId();
    }

    public Optional<Professor> findById(Long id) {
        Optional<Professor> professor = professorJpaRepository.findById(id);
        return professor;
    }

    public List<Professor> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return professorJpaRepository.findAll(pageRequest).toList();
    }

    public List<Professor> findAllByDepartmentId(Long departmentId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return professorJpaRepository.findAllByDepartment_Id(departmentId, pageRequest);
    }

    public void delete(Long id) {
        professorJpaRepository.deleteById(id);
    }
}
