package com.capstonedesign.inhalife.user.repository.jpa;

import com.capstonedesign.inhalife.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    Optional<User> findByEmailAndPassword(String email, String password);
}
