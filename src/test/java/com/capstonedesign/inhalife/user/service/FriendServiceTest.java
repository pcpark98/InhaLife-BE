package com.capstonedesign.inhalife.user.service;

import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.department.repository.DepartmentRepository;
import com.capstonedesign.inhalife.user.domain.Friend;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.dto.response.ReadFriendResponse;
import com.capstonedesign.inhalife.user.exception.DuplicatedFriendException;
import com.capstonedesign.inhalife.user.exception.DuplicatedFriendRequestException;
import com.capstonedesign.inhalife.user.exception.NotExistedFriendException;
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

        boolean 신청_이후_친구여부 = 조회한_친구.getIsFriend();

        friendService.acceptFriend(유저2, 유저1);

        boolean 수락_이후_친구여부 = 조회한_친구.getIsFriend();

        // then
        assertEquals(조회한_친구.getId(), 생성한_아이디);
        assertEquals(false, 신청_이후_친구여부);
        assertEquals(true, 수락_이후_친구여부);
    }

    @Test(expected = DuplicatedFriendException.class)
    public void 이미_친구인_유저에게_친구신청_오류() {
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

        // configure friend relationship
        friendService.requestFriend(유저1, 유저2);
        friendService.acceptFriend(유저2, 유저1);


        // when
        friendService.requestFriend(유저1, 유저2);


        // then
        // DuplicatedFriendException 발생
    }

    @Test(expected = DuplicatedFriendRequestException.class)
    public void 이미_친구요청을_보낸_유저에게_또_친구신청_오류() {
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

        // configure friend relationship
        friendService.requestFriend(유저1, 유저2);


        // when
        friendService.requestFriend(유저1, 유저2);


        // then
        // DuplicatedFriendRequestException 발생
    }

    @Test(expected = NotExistedFriendException.class)
    public void 없는_친구요청_수락_오류() {
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
        friendService.acceptFriend(유저2, 유저1);


        // then
        // NotExistedFriendException 발생
    }

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

    @Test
    public void 모든_친구_요청_리스트_조회() {
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
        User 유저4 = new User(
                "email4@email.com",
                "password4",
                학과,
                "nickname4",
                1,
                20,
                true
        );
        userRepository.save(유저1);
        userRepository.save(유저2);
        userRepository.save(유저3);
        userRepository.save(유저4);

        // configure friend relationship
        friendService.requestFriend(유저2, 유저1);
        friendService.acceptFriend(유저1, 유저2);

        friendService.requestFriend(유저3, 유저1);
        friendService.requestFriend(유저4, 유저1);


        // when
        List<ReadFriendResponse> 유저1이_받은_친구요청 = friendService.getAllReceivedFriendRequest(유저1.getId());


        // then
        assertEquals(2, 유저1이_받은_친구요청.size());
    }
}