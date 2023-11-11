package com.capstonedesign.inhalife.user.repository;

import com.capstonedesign.inhalife.user.domain.Hobby;
import com.capstonedesign.inhalife.user.repository.jpa.HobbyJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HobbyRepository {

    private final HobbyJpaRepository hobbyJpaRepository;

    public Long save(Hobby hobby) {
        return hobbyJpaRepository.save(hobby).getId();
    }

    public Optional<Hobby> findById(Long id) {
        Optional<Hobby> hobby = hobbyJpaRepository.findById(id);
        return hobby;
    }

    public List<Hobby> findAll() {
        return hobbyJpaRepository.findAll();
    }

    public void delete(Long id) {
        hobbyJpaRepository.deleteById(id);
    }
}
