package com.capstonedesign.inhalife.user;

import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.dto.request.CreateFriendRequest;
import com.capstonedesign.inhalife.user.dto.response.ReadFriendResponse;
import com.capstonedesign.inhalife.user.service.AuthorizationService;
import com.capstonedesign.inhalife.user.service.FriendService;
import com.capstonedesign.inhalife.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FriendController {

    private final UserService userService;
    private final FriendService friendService;
    private final AuthorizationService authorizationService;

    @PostMapping("/friend")
    public ResponseEntity<Void> createFriend(
            @RequestBody @Valid CreateFriendRequest request) {
        authorizationService.checkSession(request.getFromUserId());

        User fromUser = userService.getById(request.getFromUserId());
        User toUser = userService.getById(request.getToUserId());

        if(request.isType()) friendService.acceptFriend(fromUser, toUser);
        else friendService.requestFriend(fromUser, toUser);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userIndex}/friend")
    public ResponseEntity<List<ReadFriendResponse>> getAllFriend(
            @PathVariable Long userIndex) {
        authorizationService.checkSession(userIndex);

        User user = userService.getById(userIndex);
        List<ReadFriendResponse> friendList = friendService.getAllFriend(userIndex);

        return ResponseEntity.ok(friendList);
    }

    @GetMapping("/user/{userIndex}/friend/received")
    public ResponseEntity<List<ReadFriendResponse>> getAllReceivedFriendRequest(
            @PathVariable Long userIndex) {
        authorizationService.checkSession(userIndex);

        User user = userService.getById(userIndex);
        List<ReadFriendResponse> friendRequestList = friendService.getAllReceivedFriendRequest(userIndex);

        return ResponseEntity.ok(friendRequestList);
    }
}
