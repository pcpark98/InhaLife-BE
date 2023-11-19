package com.capstonedesign.inhalife.user.service;

import com.capstonedesign.inhalife.user.domain.Friend;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.exception.DuplicatedFriendException;
import com.capstonedesign.inhalife.user.exception.DuplicatedFriendRequestException;
import com.capstonedesign.inhalife.user.exception.NotExistedFriendException;
import com.capstonedesign.inhalife.user.exception.NotExistedUserException;
import com.capstonedesign.inhalife.user.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;

    @Transactional
    public Long requestFriend(User fromUser, User toUser) {
        Optional<Friend> duplicatedFriend = friendRepository.findByFromUserIndexAndToUserIndex(fromUser.getId(), toUser.getId());
        if(duplicatedFriend.isPresent()) {
            if(duplicatedFriend.get().isFriend()) throw new DuplicatedFriendException();
            else throw new DuplicatedFriendRequestException();
        }

        Friend friend = new Friend(fromUser.getId(), toUser.getId());

        return friendRepository.save(friend);
    }

    public Friend getByFromUserIdAndToUserId(Long fromUserId, Long toUserId) {
        if(fromUserId == null || toUserId == null) throw new NotExistedUserException();

        Optional<Friend> friend = friendRepository.findByFromUserIndexAndToUserIndex(fromUserId, toUserId);
        if(!friend.isPresent()) throw new NotExistedFriendException();

        return friend.get();
    }
}
