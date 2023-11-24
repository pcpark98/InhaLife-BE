package com.capstonedesign.inhalife.subject.service;

import com.capstonedesign.inhalife.subject.domain.SubjectTaken;
import com.capstonedesign.inhalife.subject.dto.response.ReadSubjectTakenResponse;
import com.capstonedesign.inhalife.subject.exception.NotExistedSubjectTakenException;
import com.capstonedesign.inhalife.subject.repository.SubjectTakenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubjectTakenService {

    private final SubjectTakenRepository subjectTakenRepository;

    @Transactional
    public Long createSubjectTaken(SubjectTaken subjectTaken) {
        return subjectTakenRepository.save(subjectTaken);
    }

    public SubjectTaken getById(Long id) {
        if(id == null) throw new NotExistedSubjectTakenException();

        Optional<SubjectTaken> subjectTaken = subjectTakenRepository.findById(id);
        if(!subjectTaken.isPresent()) throw new NotExistedSubjectTakenException();

        return subjectTaken.get();
    }

    public List<ReadSubjectTakenResponse> getAllByUserId(Long userId, int page, int size) {
        List<ReadSubjectTakenResponse> responseList = new ArrayList<>();

        List<SubjectTaken> subjectTakenList = subjectTakenRepository.findAllByUserIndex(userId, page, size);
        subjectTakenList.forEach(subjectTaken -> {
            responseList.add(
                    new ReadSubjectTakenResponse(
                            subjectTaken.getId(),
                            subjectTaken.getSubject().getId(),
                            subjectTaken.getSubject().getName(),
                            subjectTaken.getSubject().getProfessor().getId(),
                            subjectTaken.getSubject().getProfessor().getName(),
                            subjectTaken.getSchoolYear(),
                            subjectTaken.getSemester()
                    )
            );
        });

        return responseList;
    }

    public List<ReadSubjectTakenResponse> getAllBySubjectNameAndUserId(String subjectName, Long userId) {
        List<ReadSubjectTakenResponse> responseList = new ArrayList<>();

        List<SubjectTaken> subjectTakenList = subjectTakenRepository.findAllBySubjectNameAndUserIndex(subjectName, userId);
        subjectTakenList.forEach(subjectTaken -> {
            responseList.add(
                    new ReadSubjectTakenResponse(
                            subjectTaken.getId(),
                            subjectTaken.getSubject().getId(),
                            subjectTaken.getSubject().getName(),
                            subjectTaken.getSubject().getProfessor().getId(),
                            subjectTaken.getSubject().getProfessor().getName(),
                            subjectTaken.getSchoolYear(),
                            subjectTaken.getSemester()
                    )
            );
        });

        return responseList;
    }

    public ReadSubjectTakenResponse getByUserIdAndSubjectId(Long userId, Long subjectId) {
        Optional<SubjectTaken> subjectTaken = subjectTakenRepository.findByUserIdAndSubjectId(userId, subjectId);
        if(!subjectTaken.isPresent()) throw new NotExistedSubjectTakenException();

        return new ReadSubjectTakenResponse(
                subjectTaken.get().getId(),
                subjectTaken.get().getSubject().getId(),
                subjectTaken.get().getSubject().getName(),
                subjectTaken.get().getSubject().getProfessor().getId(),
                subjectTaken.get().getSubject().getProfessor().getName(),
                subjectTaken.get().getSchoolYear(),
                subjectTaken.get().getSemester()
        );
    }
}
