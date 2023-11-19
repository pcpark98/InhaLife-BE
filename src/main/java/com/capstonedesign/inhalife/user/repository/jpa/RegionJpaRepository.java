package com.capstonedesign.inhalife.user.repository.jpa;

import com.capstonedesign.inhalife.user.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionJpaRepository extends JpaRepository<Region, Long> {
}
