package com.capstonedesign.inhalife.department.repository;

import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.department.repository.jpa.DepartmentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DepartmentRepository {

    private final DepartmentJpaRepository departmentJpaRepository;

    public Long save(Department department) {
        return departmentJpaRepository.save(department).getId();
    }

    public Optional<Department> findById(Long id) {
        Optional<Department> department = departmentJpaRepository.findById(id);
        return department;
    }

    public List<Department> findAll() {
        List<Department> departmentList = departmentJpaRepository.findAll();
        return departmentList;
    }

    public Optional<Department> findByName(String name) {
        Optional<Department> department = departmentJpaRepository.findByName(name);
        return department;
    }

    public void delete(Long id) {
        departmentJpaRepository.deleteById(id);
    }
}