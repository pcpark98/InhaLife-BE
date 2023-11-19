package com.capstonedesign.inhalife.user.repository;

import com.capstonedesign.inhalife.user.domain.Friend;
import com.capstonedesign.inhalife.user.repository.jpa.FriendJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FriendRepository {

    private final FriendJpaRepository friendJpaRepository;

    public Long save(Friend friend) {
        return friendJpaRepository.save(friend).getId();
    }

    public Optional<Friend> findByFromUserIndexAndToUserIndex(Long fromUserIndex, Long toUserIndex) {
        Optional<Friend> friend = friendJpaRepository.findByFromUserIndexAndToUserIndex(fromUserIndex, toUserIndex);
        return friend;
    }
}