package com.capstonedesign.inhalife.department.repository.jpa;

import com.capstonedesign.inhalife.department.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentJpaRepository extends JpaRepository<Department, Long> {
}
