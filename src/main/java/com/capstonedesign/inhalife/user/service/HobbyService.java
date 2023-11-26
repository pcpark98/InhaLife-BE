package com.capstonedesign.inhalife.user.service;

import com.capstonedesign.inhalife.user.domain.Hobby;
import com.capstonedesign.inhalife.user.dto.response.GetHobbyResponse;
import com.capstonedesign.inhalife.user.exception.NotExistedHobbyException;
import com.capstonedesign.inhalife.user.repository.HobbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HobbyService {

    private final HobbyRepository hobbyRepository;

    public Hobby getById(Long id) {
        if(id == null) throw new NotExistedHobbyException();

        Optional<Hobby> hobby = hobbyRepository.findById(id);
        if(!hobby.isPresent()) throw new NotExistedHobbyException();

        return hobby.get();
    }

    public List<GetHobbyResponse> getAll() {

        return hobbyRepository.findAll().stream()
                .map( h -> {
                    return new GetHobbyResponse(
                            h.getId(),
                            h.getName());
                })
                .collect(Collectors.toList());
    }

    public Hobby getByName(String name) {
        if(name == null) throw new NotExistedHobbyException();

        Optional<Hobby> hobby = hobbyRepository.findByName(name);
        if(!hobby.isPresent()) throw new NotExistedHobbyException();

        return hobby.get();
    }
}
