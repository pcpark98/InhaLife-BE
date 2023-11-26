package com.capstonedesign.inhalife.contest.repository.jpa;

import com.capstonedesign.inhalife.contest.domain.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FieldJpaRepository extends JpaRepository<Field, Long> {
    Optional<Field> findByName(String name);
}
