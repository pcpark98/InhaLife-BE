package com.capstonedesign.inhalife.subject.service;

import com.capstonedesign.inhalife.subject.domain.Subject;
import com.capstonedesign.inhalife.subject.dto.response.ReadSubjectResponse;
import com.capstonedesign.inhalife.subject.exception.NotExistedSubjectException;
import com.capstonedesign.inhalife.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public Subject getById(Long id) {
        if(id == null) throw new NotExistedSubjectException();

        Optional<Subject> subject = subjectRepository.findById(id);
        if(!subject.isPresent()) throw new NotExistedSubjectException();

        return subject.get();
    }

    public List<ReadSubjectResponse> getAll(int page, int size) {
        List<ReadSubjectResponse> responseList = new ArrayList<>();

        List<Subject> subjectList = subjectRepository.findAll(page, size);
        subjectList.forEach(subject -> {
            responseList.add(
                    new ReadSubjectResponse(
                            subject.getId(),
                            subject.getDepartment().getId(),
                            subject.getDepartment().getName(),
                            subject.getProfessor().getId(),
                            subject.getProfessor().getName(),
                            subject.getName(),
                            subject.getSubjectType().getName(),
                            subject.getEvaluationType(),
                            subject.getSchoolYear(),
                            subject.getSemester(),
                            subject.getIsOnline()
                    )
            );
        });

        return responseList;
    }

    public List<ReadSubjectResponse> getAllByDepartmentId(Long departmentId, int page, int size) {
        List<ReadSubjectResponse> responseList = new ArrayList<>();

        List<Subject> subjectList = subjectRepository.findAllByDepartmentId(departmentId, page, size);
        subjectList.forEach(subject -> {
            responseList.add(
                    new ReadSubjectResponse(
                            subject.getId(),
                            subject.getDepartment().getId(),
                            subject.getDepartment().getName(),
                            subject.getProfessor().getId(),
                            subject.getProfessor().getName(),
                            subject.getName(),
                            subject.getSubjectType().getName(),
                            subject.getEvaluationType(),
                            subject.getSchoolYear(),
                            subject.getSemester(),
                            subject.getIsOnline()
                    )
            );
        });

        return responseList;
    }

    public List<ReadSubjectResponse> getAllByName(String name, int page, int size) {
        List<ReadSubjectResponse> responseList = new ArrayList<>();

        List<Subject> subjectList = subjectRepository.findAllByName(name, page, size);
        subjectList.forEach(subject -> {
            responseList.add(
                    new ReadSubjectResponse(
                            subject.getId(),
                            subject.getDepartment().getId(),
                            subject.getDepartment().getName(),
                            subject.getProfessor().getId(),
                            subject.getProfessor().getName(),
                            subject.getName(),
                            subject.getSubjectType().getName(),
                            subject.getEvaluationType(),
                            subject.getSchoolYear(),
                            subject.getSemester(),
                            subject.getIsOnline()
                    )
            );
        });

        return responseList;
    }

    /*
    public ReadSubjectResponse getBySubjectNameAndProfessorName(String subjectName, String professorName) {
        Optional<Subject> subject = subjectRepository.findByNameAndProfessorName(subjectName, professorName);
        if(!subject.isPresent()) throw new NotExistedSubjectException();

        return new ReadSubjectResponse(
                subject.get().getId(),
                subject.get().getDepartment().getId(),
                subject.get().getDepartment().getName(),
                subject.get().getProfessor().getId(),
                subject.get().getProfessor().getName(),
                subject.get().getName(),
                subject.get().getSubjectType().getName(),
                subject.get().getEvaluationType(),
                subject.get().getSchoolYear(),
                subject.get().getSemester(),
                subject.get().getIsOnline()
        );
    }
     */
}
