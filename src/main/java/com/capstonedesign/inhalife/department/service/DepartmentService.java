package com.capstonedesign.inhalife.department.service;

import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.department.exception.NotExistedDepartmentException;
import com.capstonedesign.inhalife.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional
    public Long addDepartment(String name) {
        Department newDepartment = new Department(name);
        return departmentRepository.save(newDepartment);
    }

    public Department getById(Long id) {
        if(id == null) throw new NotExistedDepartmentException();

        Optional<Department> department = departmentRepository.findById(id);
        if(!department.isPresent()) throw new NotExistedDepartmentException();

        return department.get();
    }

    public Department getByName(String name) {
        if(name == null) throw new  NotExistedDepartmentException();

        Optional<Department> department = departmentRepository.findByName(name);
        if(!department.isPresent()) throw new NotExistedDepartmentException();

        return department.get();
    }

    public List<Department> getAll() {
        List<Department> departmentList = departmentRepository.findAll();
        return departmentList;
    }

    @Transactional
    public void delete(Long id) {
        if(id == null) throw new NotExistedDepartmentException();

        departmentRepository.delete(id);
    }
}
