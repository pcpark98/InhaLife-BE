package com.capstonedesign.inhalife.user.service;

import com.capstonedesign.inhalife.user.domain.HobbyOfUser;
import com.capstonedesign.inhalife.user.dto.response.GetHobbyResponse;
import com.capstonedesign.inhalife.user.exception.DuplicatedHobbyOfUserException;
import com.capstonedesign.inhalife.user.repository.HobbyOfUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<GetHobbyResponse> getAllHobby(Long userId) {
        return hobbyOfUserRepository.findByUserIndex(userId).stream()
                .map( hou -> {
                    return new GetHobbyResponse(
                            hou.getHobby().getId(),
                            hou.getHobby().getName());
                })
                .collect(Collectors.toList());
    }

    public void deleteHobby(Long userId, Long hobbyId) {
        Optional<HobbyOfUser> hobbyOfUser = hobbyOfUserRepository.findByUserIndexAndHobbyIndex(userId, hobbyId);
        hobbyOfUserRepository.delete(hobbyOfUser.get());
    }
}
