package com.capstonedesign.inhalife.department.repository.jpa;

import com.capstonedesign.inhalife.department.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentJpaRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
}
