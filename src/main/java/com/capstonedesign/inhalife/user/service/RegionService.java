package com.capstonedesign.inhalife.user.service;

import com.capstonedesign.inhalife.user.domain.Region;
import com.capstonedesign.inhalife.user.dto.response.GetRegionResponse;
import com.capstonedesign.inhalife.user.exception.NotExistedRegionException;
import com.capstonedesign.inhalife.user.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    public Region getById(Long id) {
        if(id == null) throw new NotExistedRegionException();

        Optional<Region> region = regionRepository.findById(id);
        if(!region.isPresent()) throw new NotExistedRegionException();

        return region.get();
    }

    public List<GetRegionResponse> getAll() {
        return regionRepository.findAll().stream()
                .map( r -> {
                    return new GetRegionResponse(
                            r.getId(),
                            r.getName());
                })
                .collect(Collectors.toList());
    }
}
