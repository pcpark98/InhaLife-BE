package com.capstonedesign.inhalife.user.repository.jpa;

import com.capstonedesign.inhalife.user.domain.HobbyOfUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HobbyOfUserJpaRepository extends JpaRepository<HobbyOfUser, Long> {

    Optional<HobbyOfUser> findByUser_IdAndHobby_Id(Long userIndex, Long hobbyIndex);
}
