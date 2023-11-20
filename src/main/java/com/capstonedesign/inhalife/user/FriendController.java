package com.capstonedesign.inhalife.user;

import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.dto.request.CreateFriendRequest;
import com.capstonedesign.inhalife.user.dto.response.ReadFriendResponse;
import com.capstonedesign.inhalife.user.service.AuthorizationService;
import com.capstonedesign.inhalife.user.service.FriendService;
import com.capstonedesign.inhalife.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "친구관계 API", description = "유저의 친구 관계 관련 API")
@RestController
@RequiredArgsConstructor
public class FriendController {

    private final UserService userService;
    private final FriendService friendService;
    private final AuthorizationService authorizationService;

    @Operation(summary = "친구 등록 API", description = "유저간의 친구 관계를 등록합니다.")
    @Parameters({
            @Parameter(name = "fromUserId", description = "친구 신청을 보낸 유저의 id"),
            @Parameter(name = "toUserId", description = "친구 신청을 받은 유저의 id" ),
            @Parameter(name = "type", description = "친구신청/친구수락")
    })
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

    @Operation(summary = "유저의 친구 리스트 조회 API", description = "유저의 모든 친구 리스트를 조회합니다.")
    @Parameter(name = "userIndex", description = "친구 목록을 조회할 유저의 id")
    @GetMapping("/user/{userIndex}/friend")
    public ResponseEntity<List<ReadFriendResponse>> getAllFriend(
            @PathVariable Long userIndex) {
        authorizationService.checkSession(userIndex);

        User user = userService.getById(userIndex);
        List<ReadFriendResponse> friendList = friendService.getAllFriend(userIndex);

        return ResponseEntity.ok(friendList);
    }

    @Operation(summary = "유저가 받은 친구 신청 목록 조회 API", description = "유저가 받은 친구 신청 목록을 조회합니다.")
    @Parameter(name = "userIndex", description = "받은 친구 신청 목록을 조회할 유저의 id")
    @GetMapping("/user/{userIndex}/friend/received")
    public ResponseEntity<List<ReadFriendResponse>> getAllReceivedFriendRequest(
            @PathVariable Long userIndex) {
        authorizationService.checkSession(userIndex);

        User user = userService.getById(userIndex);
        List<ReadFriendResponse> friendRequestList = friendService.getAllReceivedFriendRequest(userIndex);

        return ResponseEntity.ok(friendRequestList);
    }

    @Operation(summary = "유저가 보낸 친구 신청 목록 조회 API", description = "유저가 보낸 친구 신청 목록을 조회합니다.")
    @Parameter(name = "userIndex", description = "보낸 친구 신청 목록을 조회할 유저의 id")
    @GetMapping("/user/{userIndex}/friend/sending")
    public ResponseEntity<List<ReadFriendResponse>> getAllSendingFriendRequest(
            @PathVariable Long userIndex) {
        authorizationService.checkSession(userIndex);

        User user = userService.getById(userIndex);
        List<ReadFriendResponse> friendRequestList = friendService.getAllSendFriendRequest(userIndex);

        return ResponseEntity.ok(friendRequestList);
    }
}
