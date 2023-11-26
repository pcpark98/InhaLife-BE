package com.capstonedesign.inhalife.user.repository.jpa;

import com.capstonedesign.inhalife.user.domain.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HobbyJpaRepository extends JpaRepository<Hobby, Long> {
    Optional<Hobby> findByName(String name);
}
