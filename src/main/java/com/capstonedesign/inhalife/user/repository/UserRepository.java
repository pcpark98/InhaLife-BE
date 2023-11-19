package com.capstonedesign.inhalife.user.repository;

import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.repository.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserJpaRepository userJpaRepository;

    public Long save(User user) {
        return userJpaRepository.save(user).getId();
    }

    public Optional<User> findById(Long id) {
        Optional<User> user = userJpaRepository.findById(id);
        return user;
    }

    public Optional<User> findByEmail(String email) {
        Optional<User> user = userJpaRepository.findByEmail(email);
        return user;
    }

    public Optional<User> findByNickname(String nickname) {
        Optional<User> user = userJpaRepository.findByNickname(nickname);
        return user;
    }

    public Optional<User> login(String email, String password) {
        Optional<User> user = userJpaRepository.findByEmailAndPassword(email, password);
        return user;
    }

    public void delete(Long id) {
        userJpaRepository.deleteById(id);
    }
}