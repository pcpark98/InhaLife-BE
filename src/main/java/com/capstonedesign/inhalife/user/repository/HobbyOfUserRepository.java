package com.capstonedesign.inhalife.user.repository;

import com.capstonedesign.inhalife.user.domain.HobbyOfUser;
import com.capstonedesign.inhalife.user.repository.jpa.HobbyOfUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HobbyOfUserRepository {

    private final HobbyOfUserJpaRepository hobbyOfUserJpaRepository;

    public Long save(HobbyOfUser hobbyOfUser) {
        return hobbyOfUserJpaRepository.save(hobbyOfUser).getId();
    }

    public Optional<HobbyOfUser> findByUserIndexAndHobbyIndex(Long userId, Long hobbyId) {
        Optional<HobbyOfUser> hobbyOfUser = hobbyOfUserJpaRepository.findByUser_IdAndHobby_Id(userId, hobbyId);
        return hobbyOfUser;
    }

    public List<HobbyOfUser> findByUserIndex(Long userId) {
        return hobbyOfUserJpaRepository.findAllByUser_Id(userId);
    }
}
