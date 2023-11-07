package com.capstonedesign.inhalife.department.service;

import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.department.exception.NotExistedDepartmentException;
import com.capstonedesign.inhalife.department.repository.DepartmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DepartmentServiceTest {

    @Autowired DepartmentService departmentService;

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    public void 학과를_등록_및_조회할_수_있다() {
        // given
        String 학과명 = "컴퓨터공학과";


        // when
        Long 등록된_학과Id = departmentService.addDepartment(학과명);
        Department 조회된_학과 = departmentService.getById(등록된_학과Id);
        List<Department> 전체_학과_리스트 = departmentService.getAll();


        // then
        assertThat(조회된_학과.getId()).isEqualTo(등록된_학과Id);
        assertEquals(1, 전체_학과_리스트.size());
    }

    @Test(expected = NotExistedDepartmentException.class)
    public void 학과를_삭제할_수_있다() {
        // given
        Department department = new Department("컴퓨터공학과");
        Long 등록된_학과_id = departmentRepository.save(department);


        // when
        departmentService.delete(등록된_학과_id);
        departmentService.getById(등록된_학과_id);


        // then
        // NotExistedDepartmentException 발생.
    }
}