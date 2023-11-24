package com.capstonedesign.inhalife.subject.service;

import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.department.repository.DepartmentRepository;
import com.capstonedesign.inhalife.subject.domain.Professor;
import com.capstonedesign.inhalife.subject.domain.Subject;
import com.capstonedesign.inhalife.subject.repository.ProfessorRepository;
import com.capstonedesign.inhalife.subject.repository.SubjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SubjectServiceTest {

    @Autowired SubjectService subjectService;

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @Test
    public void 과목_조회() {
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

        Subject 과목 = new Subject(
                학과,
                교수1,
                "과목1",
                "전공선택",
                true,
                1,
                true,
                true
        );
        Subject 과목2 = new Subject(
                학과,
                교수2,
                "과목2",
                "전공선택",
                true,
                1,
                true,
                true
        );
        Subject 과목3 = new Subject(
                학과2,
                교수3,
                "과목1",
                "전공선택",
                true,
                1,
                true,
                true
        );
        subjectRepository.save(과목);
        subjectRepository.save(과목2);
        subjectRepository.save(과목3);


        // when
        Subject 조회한_과목 = subjectService.getById(과목.getId());
        int 전체_과목의_수 = subjectService.getAll(0, 10).size();
        int 학과의_전체_과목의_수 = subjectService.getAllByDepartmentId(학과.getId(), 0, 10).size();
        int 학과2의_전체_과목의_수 = subjectService.getAllByDepartmentId(학과2.getId(), 0, 10).size();
        int 과목명이_과목1인_과목의_수 = subjectService.getAllByName("과목1", 0, 10).size();
        int 과목명이_과목3인_과목의_수 = subjectService.getAllByName("과목3", 0, 10).size();


        // then
        assertEquals(과목, 조회한_과목);
        assertEquals(3, 전체_과목의_수);
        assertEquals(2, 학과의_전체_과목의_수);
        assertEquals(1, 학과2의_전체_과목의_수);
        assertEquals(2, 과목명이_과목1인_과목의_수);
        assertEquals(0, 과목명이_과목3인_과목의_수);
    }
}