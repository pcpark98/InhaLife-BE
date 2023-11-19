package com.capstonedesign.inhalife.user.service;

import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.department.repository.DepartmentRepository;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.repository.UserRepository;
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
public class FriendServiceTest {

    @Autowired FriendService friendService;

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void 친구_요청_및_조회() {
        // given
        // configure department
        Department 학과 = new Department("컴퓨터공학과");
        departmentRepository.save(학과);

        // configure user info
        User 유저1 = new User(
                "email@email.com",
                "password",
                학과,
                "nickname",
                1,
                20,
                true
        );
        User 유저2 = new User(
                "email2@email.com",
                "password2",
                학과,
                "nickname2",
                1,
                20,
                true
        );
        userRepository.save(유저1);
        userRepository.save(유저2);


        // when
        Long 생성한_아이디 = friendService.requestFriend(유저1, 유저2);
        Long 조회한_아이디 = friendService.getByFromUserIdAndToUserId(유저1.getId(), 유저2.getId()).getId();


        // then
        assertEquals(조회한_아이디, 생성한_아이디);
    }
}