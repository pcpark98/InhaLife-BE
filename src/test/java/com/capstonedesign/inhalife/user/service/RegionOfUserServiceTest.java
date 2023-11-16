package com.capstonedesign.inhalife.user.service;

import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.department.repository.DepartmentRepository;
import com.capstonedesign.inhalife.user.domain.Region;
import com.capstonedesign.inhalife.user.domain.RegionOfUser;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.exception.DuplicatedRegionOfUserException;
import com.capstonedesign.inhalife.user.repository.RegionOfUserRepository;
import com.capstonedesign.inhalife.user.repository.RegionRepository;
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
public class RegionOfUserServiceTest {

    @Autowired RegionOfUserService regionOfUserService;

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    RegionOfUserRepository regionOfUserRepository;

    @Test
    public void 지역을_등록할수_있다() {
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

        // configure Region info
        Region 지역1 = new Region("인천");
        Region 지역2 = new Region("서울");
        regionRepository.save(지역1);
        regionRepository.save(지역2);

        // configure region of user
        RegionOfUser 유저의_지역 = new RegionOfUser(유저, 지역1);


        // when
        Long 유저의_지역_id = regionOfUserService.setRegion(유저의_지역);
        boolean 지역1_여부 = regionOfUserService.isRegion(유저.getId(), 지역1.getId());
        boolean 지역2_여부 = regionOfUserService.isRegion(유저.getId(), 지역2.getId());


        // then
        assertEquals(true, 지역1_여부);
        assertEquals(false, 지역2_여부);
    }

    @Test(expected = DuplicatedRegionOfUserException.class)
    public void 같은_지역을_등록할_수_없다() {
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

        // configure Region info
        Region 지역1 = new Region("인천");
        regionRepository.save(지역1);

        // configure region of user
        RegionOfUser 유저의_지역 = new RegionOfUser(유저, 지역1);
        RegionOfUser 유저의_지역2 = new RegionOfUser(유저, 지역1);


        // when
        regionOfUserService.setRegion(유저의_지역);
        regionOfUserService.setRegion(유저의_지역2);


        // then
        // DuplicatedRegionOfUserException 발생
    }

    @Test
    public void 유저의_모든_지역을_조회할_수_있다() {
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

        // configure Region info
        Region 지역1 = new Region("인천");
        Region 지역2 = new Region("서울");
        regionRepository.save(지역1);
        regionRepository.save(지역2);

        // configure region of user
        RegionOfUser 유저의_지역 = new RegionOfUser(유저, 지역1);
        RegionOfUser 유저의_지역2 = new RegionOfUser(유저, 지역2);
        regionOfUserRepository.save(유저의_지역);
        regionOfUserRepository.save(유저의_지역2);


        // when
        int 유저의_총_지역_개수 = regionOfUserService.getUsersAllRegion(유저.getId()).size();


        // then
        assertEquals(2, 유저의_총_지역_개수);
    }

    @Test
    public void 유저의_지역을_삭제할_수_있다() {
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

        // configure Region info
        Region 지역1 = new Region("인천");
        regionRepository.save(지역1);

        // configure region of user
        RegionOfUser 유저의_지역 = new RegionOfUser(유저, 지역1);
        regionOfUserRepository.save(유저의_지역);


        // when
        boolean 삭제_이전 = regionOfUserService.isRegion(유저.getId(), 지역1.getId());
        regionOfUserService.deleteRegion(유저.getId(), 지역1.getId());
        boolean 삭제_이후 = regionOfUserService.isRegion(유저.getId(), 지역1.getId());


        // then
        assertEquals(true, 삭제_이전);
        assertEquals(false, 삭제_이후);
    }
}