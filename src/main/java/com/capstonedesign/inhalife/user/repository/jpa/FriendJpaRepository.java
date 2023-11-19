package com.capstonedesign.inhalife.user.repository.jpa;

import com.capstonedesign.inhalife.user.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendJpaRepository extends JpaRepository<Friend, Long> {

    Optional<Friend> findByFromUserIndexAndToUserIndex(Long fromUserIndex, Long toUserIndex);

    Optional<Friend> findByFromUserIndex(Long fromUserIndex);

    Optional<Friend> findByToUserIndex(Long toUserIndex);
}
