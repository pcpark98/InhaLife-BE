package com.capstonedesign.inhalife.user.service;

import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.department.repository.DepartmentRepository;
import com.capstonedesign.inhalife.user.domain.Friend;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.dto.response.ReadFriendResponse;
import com.capstonedesign.inhalife.user.repository.UserRepository;
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
public class FriendServiceTest {

    @Autowired FriendService friendService;

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void 친구_요청_조회_수락() {
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
        Friend 조회한_친구 = friendService.getByFromUserIdAndToUserId(유저1.getId(), 유저2.getId());

        boolean 신청_이후_친구여부 = 조회한_친구.isFriend();

        friendService.acceptFriend(유저2, 유저1);

        boolean 수락_이후_친구여부 = 조회한_친구.isFriend();

        // then
        assertEquals(조회한_친구.getId(), 생성한_아이디);
        assertEquals(false, 신청_이후_친구여부);
        assertEquals(true, 수락_이후_친구여부);
    }

    //TODO: 없는 친구 요청에 대한 수락 오류

    @Test
    public void 모든_친구_리스트_조회() {
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
        User 유저3 = new User(
                "email3@email.com",
                "password3",
                학과,
                "nickname3",
                1,
                20,
                true
        );
        userRepository.save(유저1);
        userRepository.save(유저2);
        userRepository.save(유저3);

        // configure friend relationship
        friendService.requestFriend(유저1, 유저2);
        friendService.acceptFriend(유저2, 유저1);

        friendService.requestFriend(유저1, 유저3);
        friendService.acceptFriend(유저3, 유저1);


        // when
        List<ReadFriendResponse> 유저1의_친구목록 = friendService.getAllFriend(유저1.getId());
        List<ReadFriendResponse> 유저2의_친구목록 = friendService.getAllFriend(유저2.getId());
        List<ReadFriendResponse> 유저3의_친구목록 = friendService.getAllFriend(유저3.getId());


        // then
        assertEquals(2, 유저1의_친구목록.size());
        assertEquals(1, 유저2의_친구목록.size());
        assertEquals(1, 유저3의_친구목록.size());
    }
}