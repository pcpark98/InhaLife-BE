package com.capstonedesign.inhalife.user.service;

import com.capstonedesign.inhalife.user.domain.RegionOfUser;
import com.capstonedesign.inhalife.user.dto.response.GetRegionResponse;
import com.capstonedesign.inhalife.user.exception.DuplicatedRegionOfUserException;
import com.capstonedesign.inhalife.user.exception.NotExistedRegionOfUserException;
import com.capstonedesign.inhalife.user.repository.RegionOfUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegionOfUserService {

    private final RegionOfUserRepository regionOfUserRepository;

    @Transactional
    public Long setRegion(RegionOfUser regionOfUser) {
        Optional<RegionOfUser> duplicatedRegion = regionOfUserRepository.findByUserIndexAndRegionIndex(regionOfUser.getUser().getId(), regionOfUser.getRegion().getId());
        if(duplicatedRegion.isPresent()) throw new DuplicatedRegionOfUserException();

        return regionOfUserRepository.save(regionOfUser);
    }

    public boolean isRegion(Long userId, Long regionId) {
        Optional<RegionOfUser> regionOfUser = regionOfUserRepository.findByUserIndexAndRegionIndex(userId, regionId);
        if(regionOfUser.isPresent()) return true;
        else return false;
    }

    public List<GetRegionResponse> getUsersAllRegion(Long userId) {
        return regionOfUserRepository.findByUserIndex(userId).stream()
                .map( rou -> {
                    return new GetRegionResponse(
                            rou.getRegion().getId(),
                            rou.getRegion().getName());
                })
                .collect(Collectors.toList());
    }

    public void deleteRegion(Long userId, Long regionId) {
        Optional<RegionOfUser> regionOfUser = regionOfUserRepository.findByUserIndexAndRegionIndex(userId, regionId);
        if(!regionOfUser.isPresent()) throw new NotExistedRegionOfUserException();

        regionOfUserRepository.delete(regionOfUser.get());
    }
}
