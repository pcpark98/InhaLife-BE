package com.capstonedesign.inhalife.contest.repository;

import com.capstonedesign.inhalife.contest.domain.Field;
import com.capstonedesign.inhalife.contest.repository.jpa.FieldJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FieldRepository {

    private final FieldJpaRepository fieldJpaRepository;

    public Long save(Field field) {
        return fieldJpaRepository.save(field).getId();
    }

    public Optional<Field> findById(Long id) {
        Optional<Field> field = fieldJpaRepository.findById(id);
        return field;
    }

    public List<Field> findAll() {
        List<Field> fieldList = fieldJpaRepository.findAll();
        return fieldList;
    }

    public Optional<Field> findByName(String name) {
        Optional<Field> field = fieldJpaRepository.findByName(name);
        return field;
    }

    public void delete(Long id) {
        fieldJpaRepository.deleteById(id);
    }
}
