package com.capstonedesign.inhalife.user.service;

import com.capstonedesign.inhalife.user.domain.Friend;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.dto.response.ReadFriendResponse;
import com.capstonedesign.inhalife.user.exception.DuplicatedFriendException;
import com.capstonedesign.inhalife.user.exception.DuplicatedFriendRequestException;
import com.capstonedesign.inhalife.user.exception.NotExistedFriendException;
import com.capstonedesign.inhalife.user.exception.NotExistedUserException;
import com.capstonedesign.inhalife.user.repository.FriendRepository;
import com.capstonedesign.inhalife.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long requestFriend(User fromUser, User toUser) {
        Optional<Friend> duplicatedFriend = friendRepository.findByFromUserIndexAndToUserIndex(fromUser.getId(), toUser.getId());
        if(duplicatedFriend.isPresent()) {
            if(duplicatedFriend.get().getIsFriend()) throw new DuplicatedFriendException();
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

    @Transactional
    public Long acceptFriend(User fromUser, User toUser) {
        Optional<Friend> friend = friendRepository.findByFromUserIndexAndToUserIndex(toUser.getId(), fromUser.getId());

        if(!friend.isPresent()) throw new NotExistedFriendException();
        if(friend.get().getIsFriend()) throw new DuplicatedFriendException();

        friend.get().setIsFriend(true);

        return friend.get().getId();
    }

    public List<ReadFriendResponse> getAllFriend(Long userId) {
        List<ReadFriendResponse> responseList = new ArrayList<>();

        List<Friend> friendList = friendRepository.findAllByFromUserIndexOrToUserIndex(userId);
        friendList.forEach(friend -> {
            User friendUser;
            if(!friend.getFromUserIndex().equals(userId)) friendUser = userRepository.findById(friend.getFromUserIndex()).get();
            else friendUser = userRepository.findById(friend.getToUserIndex()).get();

            responseList.add(
                    new ReadFriendResponse(
                            friend.getId(),
                            friendUser.getId(),
                            friendUser.getDepartment().getName(),
                            friendUser.getEmail(),
                            friendUser.getNickname(),
                            friendUser.getSchoolYear(),
                            friendUser.getAge(),
                            friendUser.isGender(),
                            friend.getCreatedAt()
                    )
            );
        });

        return responseList;
    }

    public List<ReadFriendResponse> getAllReceivedFriendRequest(Long userId) {
        List<ReadFriendResponse> responseList = new ArrayList<>();

        List<Friend> friendList = friendRepository.findAllByToUserIndexAndFriendIsFalse(userId);
        friendList.forEach(friend -> {
            User friendUser = userRepository.findById(friend.getFromUserIndex()).get();

            responseList.add(
                    new ReadFriendResponse(
                            friend.getId(),
                            friendUser.getId(),
                            friendUser.getDepartment().getName(),
                            friendUser.getEmail(),
                            friendUser.getNickname(),
                            friendUser.getSchoolYear(),
                            friendUser.getAge(),
                            friendUser.isGender(),
                            friend.getCreatedAt()
                    )
            );
        });

        return responseList;
    }
}
