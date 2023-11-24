package com.capstonedesign.inhalife.subject.repository;

import com.capstonedesign.inhalife.subject.domain.Subject;
import com.capstonedesign.inhalife.subject.repository.jpa.SubjectJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SubjectRepository {

    private final SubjectJpaRepository subjectJpaRepository;

    public Long save(Subject subject) {
        return subjectJpaRepository.save(subject).getId();
    }

    public Optional<Subject> findById(Long id) {
        Optional<Subject> subject = subjectJpaRepository.findById(id);
        return subject;
    }

    public List<Subject> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return subjectJpaRepository.findAll(pageRequest).toList();
    }

    public List<Subject> findAllByDepartmentId(Long departmentId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return subjectJpaRepository.findAllByDepartment_Id(departmentId, pageRequest);
    }

    public List<Subject> findAllByName(String name, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return subjectJpaRepository.findAllByName(name, pageRequest);
    }

    public Optional<Subject> findByNameAndProfessorName(String subjectName, String professorName) {
        Optional<Subject> subject = subjectJpaRepository.findByNameAndProfessorName(subjectName, professorName);
        return subject;
    }
}
