package com.capstonedesign.inhalife.user.service;

import com.capstonedesign.inhalife.user.domain.HobbyOfUser;
import com.capstonedesign.inhalife.user.exception.DuplicatedHobbyOfUserException;
import com.capstonedesign.inhalife.user.repository.HobbyOfUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HobbyOfUserService {

    private final HobbyOfUserRepository hobbyOfUserRepository;

    @Transactional
    public Long setHobby(HobbyOfUser hobbyOfUser) {
        Optional<HobbyOfUser> duplicatedHobby = hobbyOfUserRepository.findByUserIndexAndHobbyIndex(hobbyOfUser.getUser().getId(), hobbyOfUser.getHobby().getId());
        if(duplicatedHobby.isPresent()) throw new DuplicatedHobbyOfUserException();

        return hobbyOfUserRepository.save(hobbyOfUser);
    }

    public boolean isHobby(Long userId, Long hobbyId) {
        Optional<HobbyOfUser> hobbyOfUser = hobbyOfUserRepository.findByUserIndexAndHobbyIndex(userId, hobbyId);
        if(hobbyOfUser.isPresent()) return true;
        else return false;
    }
}
