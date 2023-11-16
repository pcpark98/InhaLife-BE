package com.capstonedesign.inhalife.user.service;

import com.capstonedesign.inhalife.user.domain.Region;
import com.capstonedesign.inhalife.user.repository.RegionRepository;
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
public class RegionServiceTest {

    @Autowired RegionService regionService;

    @Autowired
    RegionRepository regionRepository;

    @Test
    public void 지역을_조회할수_있다() {
        // given
        // configure region
        Region 지역1 = new Region("인천");
        Region 지역2 = new Region("서울");
        regionRepository.save(지역1);
        regionRepository.save(지역2);


        // when
        Region 조회한_지역 = regionService.getById(지역1.getId());
        int 총_지역_개수 = regionService.getAll().size();


        // then
        assertEquals(지역1, 조회한_지역);
        assertEquals(2, 총_지역_개수);
    }
}