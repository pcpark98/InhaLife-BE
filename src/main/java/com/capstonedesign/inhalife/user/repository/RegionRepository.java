package com.capstonedesign.inhalife.user.repository;

import com.capstonedesign.inhalife.user.domain.Region;
import com.capstonedesign.inhalife.user.repository.jpa.RegionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RegionRepository {

    private final RegionJpaRepository regionJpaRepository;

    public Long save(Region region) {
        return regionJpaRepository.save(region).getId();
    }

    public Optional<Region> findById(Long id) {
        Optional<Region> region = regionJpaRepository.findById(id);
        return region;
    }

    public List<Region> findAll() {
        return regionJpaRepository.findAll();
    }

    public void delete(Long id) {
        regionJpaRepository.deleteById(id);
    }
}
