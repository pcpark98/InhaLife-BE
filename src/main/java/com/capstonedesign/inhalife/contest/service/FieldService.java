package com.capstonedesign.inhalife.contest.service;

import com.capstonedesign.inhalife.contest.domain.Field;
import com.capstonedesign.inhalife.contest.dto.response.ReadFieldResponse;
import com.capstonedesign.inhalife.contest.exception.NotExistedFieldException;
import com.capstonedesign.inhalife.contest.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FieldService {

    private final FieldRepository fieldRepository;

    @Transactional
    public Long addField(String name) {
        Field field = new Field(name);
        return fieldRepository.save(field);
    }

    public List<ReadFieldResponse> getAll() {
        List<ReadFieldResponse> responseList = new ArrayList<>();

        List<Field> fieldList = fieldRepository.findAll();
        fieldList.forEach(field -> {
            responseList.add(
                    new ReadFieldResponse(
                            field.getId(),
                            field.getName()
                    )
            );
        });

        return responseList;
    }

    public Field getByName(String name) {
        if(name == null) throw new NotExistedFieldException();

        Optional<Field> field = fieldRepository.findByName(name);
        if(!field.isPresent()) throw new NotExistedFieldException();

        return field.get();
    }
}