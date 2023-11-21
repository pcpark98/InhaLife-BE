package com.capstonedesign.inhalife.subject.service;

import com.capstonedesign.inhalife.subject.domain.Professor;
import com.capstonedesign.inhalife.subject.dto.response.ReadProfessorResponse;
import com.capstonedesign.inhalife.subject.exception.NotExistedProfessorException;
import com.capstonedesign.inhalife.subject.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public Professor getById(Long id) {
        if(id == null) throw new NotExistedProfessorException();

        Optional<Professor> professor = professorRepository.findById(id);
        if(!professor.isPresent()) throw new NotExistedProfessorException();

        return professor.get();
    }

    public List<ReadProfessorResponse> getAll(int page, int size) {
        List<ReadProfessorResponse> responseList = new ArrayList<>();

        List<Professor> professorList = professorRepository.findAll(page, size);
        professorList.forEach(professor -> {
            responseList.add(
                    new ReadProfessorResponse(
                            professor.getId(),
                            professor.getDepartment().getId(),
                            professor.getName()
                    )
            );
        });

        return responseList;
    }

    public List<ReadProfessorResponse> getAllByDepartmentId(Long departmentId, int page, int size) {
        List<ReadProfessorResponse> responseList = new ArrayList<>();

        List<Professor> professorList = professorRepository.findAllByDepartmentId(departmentId, page, size);
        professorList.forEach(professor -> {
            responseList.add(
                    new ReadProfessorResponse(
                            professor.getId(),
                            professor.getDepartment().getId(),
                            professor.getName()
                    )
            );
        });

        return responseList;
    }
}
