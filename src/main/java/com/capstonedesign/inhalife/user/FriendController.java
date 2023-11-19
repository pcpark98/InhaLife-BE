package com.capstonedesign.inhalife.user;

import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.dto.request.CreateFriendRequest;
import com.capstonedesign.inhalife.user.service.AuthorizationService;
import com.capstonedesign.inhalife.user.service.FriendService;
import com.capstonedesign.inhalife.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
