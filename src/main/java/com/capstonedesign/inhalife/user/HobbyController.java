package com.capstonedesign.inhalife.user;

import com.capstonedesign.inhalife.user.domain.Hobby;
import com.capstonedesign.inhalife.user.domain.HobbyOfUser;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.dto.request.SetHobbyRequest;
import com.capstonedesign.inhalife.user.dto.response.GetHobbyResponse;
import com.capstonedesign.inhalife.user.exception.NotExistedHobbyException;
import com.capstonedesign.inhalife.user.service.HobbyOfUserService;
import com.capstonedesign.inhalife.user.service.HobbyService;
import com.capstonedesign.inhalife.user.service.UserService;
import com.sun.xml.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HobbyController {

    private final UserService userService;
    private final HobbyService hobbyService;
    private final HobbyOfUserService hobbyOfUserService;

    // TODO: 모든 취미를 조회하는 API 필요

    @PostMapping("/hobby")
    public ResponseEntity<Void> setHobby(
            @RequestBody @Valid SetHobbyRequest request) {

        User user = userService.getById(request.getUserId());
        Hobby hobby = hobbyService.getById(request.getHobbyId());

        HobbyOfUser hobbyOfUser = new HobbyOfUser(user, hobby);
        hobbyOfUserService.setHobby(hobbyOfUser);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userIndex}/hobbies")
    public ResponseEntity<List<GetHobbyResponse>> getAllHobby(
            @PathVariable("userIndex") Long userId) {
        User user = userService.getById(userId);

        List<GetHobbyResponse> hobbyList = hobbyOfUserService.getAllHobby(user.getId());

        return ResponseEntity.ok(hobbyList);
    }

    @DeleteMapping("/user/{userIndex}/hobby/{hobbyIndex}")
    public ResponseEntity<Void> deleteHobby(
            @PathVariable("userIndex") Long userId,
            @PathVariable("hobbyIndex") Long hobbyId) {
        User user = userService.getById(userId);
        Hobby hobby = hobbyService.getById(hobbyId);

        if(!hobbyOfUserService.isHobby(user.getId(), hobby.getId()))
            throw new NotExistedHobbyException();

        hobbyOfUserService.deleteHobby(user.getId(), hobby.getId());

        return ResponseEntity.ok().build();
    }
}
