package com.capstonedesign.inhalife.user.service;


import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.department.repository.DepartmentRepository;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.exception.DuplicatedEmailException;
import com.capstonedesign.inhalife.user.exception.DuplicatedNicknameException;
import com.capstonedesign.inhalife.user.exception.NotExistedUserException;
import com.capstonedesign.inhalife.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired UserService userService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    public void 회원가입_및_유저조회() {
        // given
        // configure department
        Department 학과 = new Department("컴퓨터공학과");
        departmentRepository.save(학과);

        // configure user info
        User user = new User(
                "email@email.com",
                "password",
                학과,
                "nickname",
                1,
                20,
                true
        );


        // when
        Long 가입한_유저_아이디 = userService.signUp(user);
        User 조회한_유저 = userService.getById(가입한_유저_아이디);


        // then
        assertThat(조회한_유저.getId()).isEqualTo(가입한_유저_아이디);
    }

    @Test(expected = DuplicatedEmailException.class)
    public void 중복된_이메일로_회원가입할_수_없다() {
        // given
        // configure department
        Department 학과 = new Department("컴퓨터공학과");
        departmentRepository.save(학과);

        // configure user info
        User user = new User(
                "email@email.com",
                "password",
                학과,
                "nickname",
                1,
                20,
                true
        );
        User user2 = new User(
                "email@email.com",
                "password",
                학과,
                "nickname2",
                1,
                20,
                true
        );

        userRepository.save(user);


        // when
        userService.signUp(user2);


        // then
        // DuplicatedEmailException 발생
    }

    @Test(expected = DuplicatedNicknameException.class)
    public void 중복된_닉네임으로_회원가입할_수_없다() {
        // given
        // configure department
        Department 학과 = new Department("컴퓨터공학과");
        departmentRepository.save(학과);

        // configure user info
        User user = new User(
                "email@email.com",
                "password",
                학과,
                "nickname",
                1,
                20,
                true
        );
        User user2 = new User(
                "email2@email.com",
                "password",
                학과,
                "nickname",
                1,
                20,
                true
        );

        userRepository.save(user);


        // when
        userService.signUp(user2);


        // then
        // DuplicatedNicknameException 발생
    }

    @Test
    public void 로그인() {
        // given
        // configure department
        Department 학과 = new Department("컴퓨터공학과");
        departmentRepository.save(학과);

        // configure user info
        User user = new User(
                "email@email.com",
                "password",
                학과,
                "nickname",
                1,
                20,
                true
        );
        userRepository.save(user);


        // when
        User 로그인한_유저 = userService.login("email@email.com", "password");


        // then
        assertThat(로그인한_유저.getNickname()).isEqualTo(user.getNickname());
    }

    @Test(expected = NotExistedUserException.class)
    public void 로그인_실패() {
        // given


        // when
        User 로그인한_유저 = userService.login("email@email.com", "password");


        // then
        // NotExistedUserException 발생
    }
}