package com.capstonedesign.inhalife.contest.repository.jpa;

import com.capstonedesign.inhalife.contest.domain.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldJpaRepository extends JpaRepository<Field, Long> {
}
