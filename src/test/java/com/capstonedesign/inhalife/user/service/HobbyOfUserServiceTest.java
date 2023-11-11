package com.capstonedesign.inhalife.user.service;

import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.department.repository.DepartmentRepository;
import com.capstonedesign.inhalife.user.domain.Hobby;
import com.capstonedesign.inhalife.user.domain.HobbyOfUser;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.repository.HobbyRepository;
import com.capstonedesign.inhalife.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class HobbyOfUserServiceTest {

    @Autowired HobbyOfUserService hobbyOfUserService;

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    HobbyRepository hobbyRepository;

    @Test
    public void 취미를_등록할수_있다() {
        // given
        // configure department
        Department 학과 = new Department("컴퓨터공학과");
        departmentRepository.save(학과);

        // configure user info
        User 유저 = new User(
                "email@email.com",
                "password",
                학과,
                "nickname",
                1,
                20,
                true
        );
        userRepository.save(유저);

        // configure Hobby info
        Hobby 취미 = new Hobby("축구");
        Hobby 취미2 = new Hobby("농구");
        hobbyRepository.save(취미);
        hobbyRepository.save(취미2);


        // configure Hobby of user
        HobbyOfUser 유저의_취미 = new HobbyOfUser(유저, 취미);


        // when
        Long 유저의_취미_id = hobbyOfUserService.setHobby(유저의_취미);
        boolean 취미1_여부 = hobbyOfUserService.isHobby(유저.getId(), 취미.getId());
        boolean 취미2_여부 = hobbyOfUserService.isHobby(유저.getId(), 취미2.getId());


        // then
        assertEquals(true, 취미1_여부);
        assertEquals(false, 취미2_여부);
    }
}