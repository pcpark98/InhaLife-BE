package com.capstonedesign.inhalife.user.repository.jpa;

import com.capstonedesign.inhalife.user.domain.RegionOfUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionOfUserJpaRepository extends JpaRepository<RegionOfUser, Long> {

    Optional<RegionOfUser> findByUser_IdAndRegion_Id(Long userIndex, Long regionIndex);

    List<RegionOfUser> findAllByUser_Id(Long userIndex);
}
