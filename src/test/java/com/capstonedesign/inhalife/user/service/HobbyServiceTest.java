package com.capstonedesign.inhalife.user.service;

import com.capstonedesign.inhalife.user.domain.Hobby;
import com.capstonedesign.inhalife.user.repository.HobbyRepository;
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
public class HobbyServiceTest {

    @Autowired HobbyService hobbyService;

    @Autowired
    HobbyRepository hobbyRepository;

    @Test
    public void 취미를_조회할수_있다() {
        // given
        // configure hobby
        Hobby 취미1 = new Hobby("축구");
        Hobby 취미2 = new Hobby("농구");
        hobbyRepository.save(취미1);
        hobbyRepository.save(취미2);


        // when
        Hobby 조회한_취미 = hobbyService.getById(취미1.getId());
        int 총_취미_개수 = hobbyService.getAll().size();


        // then
        assertEquals(취미1, 조회한_취미);
        assertEquals(2, 총_취미_개수);
    }
}