package com.capstonedesign.inhalife.user.service;

import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.department.repository.DepartmentRepository;
import com.capstonedesign.inhalife.user.domain.Hobby;
import com.capstonedesign.inhalife.user.domain.HobbyOfUser;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.exception.DuplicatedHobbyOfUserException;
import com.capstonedesign.inhalife.user.repository.HobbyOfUserRepository;
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
    @Autowired
    HobbyOfUserRepository hobbyOfUserRepository;

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

    @Test(expected = DuplicatedHobbyOfUserException.class)
    public void 같은_취미를_등록할_수_없다() {
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
        hobbyRepository.save(취미);


        // configure Hobby of user
        HobbyOfUser 유저의_취미 = new HobbyOfUser(유저, 취미);
        HobbyOfUser 유저의_취미2 = new HobbyOfUser(유저, 취미);


        // when
        Long 유저의_취미_id = hobbyOfUserService.setHobby(유저의_취미);
        hobbyOfUserService.setHobby(유저의_취미2);


        // then
        // DuplicatedHobbyOfUserException 발생
    }

    @Test
    public void 유저의_모든_취미를_조회할_수_있다() {
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
        HobbyOfUser 유저의_취미2 = new HobbyOfUser(유저, 취미2);
        hobbyOfUserRepository.save(유저의_취미);
        hobbyOfUserRepository.save(유저의_취미2);


        // when
        int 유저의_총_취미_개수 = hobbyOfUserService.getAllHobby(유저.getId()).size();


        // then
        assertEquals(2, 유저의_총_취미_개수);
    }

    @Test
    public void 유저의_취미를_삭제할_수_있다() {
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
        hobbyRepository.save(취미);


        // configure Hobby of user
        HobbyOfUser 유저의_취미 = new HobbyOfUser(유저, 취미);
        hobbyOfUserRepository.save(유저의_취미);


        // when
        boolean 삭제_이전 = hobbyOfUserService.isHobby(유저.getId(), 취미.getId());
        hobbyOfUserService.deleteHobby(유저.getId(), 취미.getId());
        boolean 삭제_이후 = hobbyOfUserService.isHobby(유저.getId(), 취미.getId());


        // then
        assertEquals(true, 삭제_이전);
        assertEquals(false, 삭제_이후);
    }
}