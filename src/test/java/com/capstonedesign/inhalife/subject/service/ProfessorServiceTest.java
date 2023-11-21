package com.capstonedesign.inhalife.subject.service;

import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.department.repository.DepartmentRepository;
import com.capstonedesign.inhalife.subject.domain.Professor;
import com.capstonedesign.inhalife.subject.dto.response.ReadProfessorResponse;
import com.capstonedesign.inhalife.subject.repository.ProfessorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProfessorServiceTest {

    @Autowired ProfessorService professorService;

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    ProfessorRepository professorRepository;

    @Test
    public void 교수_리스트_조회() {
        // given
        // configure department
        Department 학과 = new Department("컴퓨터공학과");
        Department 학과2 = new Department("정보통신공학과");
        departmentRepository.save(학과);
        departmentRepository.save(학과2);

        // configure professor
        Professor 교수1 = new Professor(학과, "교수1");
        Professor 교수2 = new Professor(학과, "교수2");
        Professor 교수3 = new Professor(학과2, "교수3");
        professorRepository.save(교수1);
        professorRepository.save(교수2);
        professorRepository.save(교수3);


        // when
        List<ReadProfessorResponse> 모든_교수 = professorService.getAll(0, 10);
        List<ReadProfessorResponse> 학과의_교수 = professorService.getAllByDepartmentId(학과.getId(), 0, 10);
        List<ReadProfessorResponse> 학과2의_교수 = professorService.getAllByDepartmentId(학과2.getId(), 0, 10);


        // then
        assertEquals(3, 모든_교수.size());
        assertEquals(2, 학과의_교수.size());
        assertEquals(1, 학과2의_교수.size());
    }
}