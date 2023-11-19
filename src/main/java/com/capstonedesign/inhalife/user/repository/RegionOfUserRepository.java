package com.capstonedesign.inhalife.user.repository;

import com.capstonedesign.inhalife.user.domain.RegionOfUser;
import com.capstonedesign.inhalife.user.repository.jpa.RegionOfUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RegionOfUserRepository {

    private final RegionOfUserJpaRepository regionOfUserJpaRepository;

    public Long save(RegionOfUser regionOfUser) {
        return regionOfUserJpaRepository.save(regionOfUser).getId();
    }

    public Optional<RegionOfUser> findByUserIndexAndRegionIndex(Long userId, Long regionId) {
        Optional<RegionOfUser> regionOfUser = regionOfUserJpaRepository.findByUser_IdAndRegion_Id(userId, regionId);
        return regionOfUser;
    }

    public List<RegionOfUser> findByUserIndex(Long userId) {
        return regionOfUserJpaRepository.findAllByUser_Id(userId);
    }

    public void delete(RegionOfUser regionOfUser) {
        regionOfUserJpaRepository.deleteById(regionOfUser.getId());
    }
}
