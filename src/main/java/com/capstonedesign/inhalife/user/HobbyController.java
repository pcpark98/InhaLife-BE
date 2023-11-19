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

    @GetMapping("/hobby")
    public ResponseEntity<List<GetHobbyResponse>> getAllHobby() {
        List<GetHobbyResponse> hobbyList = hobbyService.getAll();

        return ResponseEntity.ok(hobbyList);
    }

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
    public ResponseEntity<List<GetHobbyResponse>> getUsersAllHobby(
            @PathVariable Long userIndex) {
        User user = userService.getById(userIndex);

        List<GetHobbyResponse> hobbyList = hobbyOfUserService.getAllHobby(user.getId());

        return ResponseEntity.ok(hobbyList);
    }

    @DeleteMapping("/user/{userIndex}/hobby/{hobbyIndex}")
    public ResponseEntity<Void> deleteHobby(
            @PathVariable Long userIndex,
            @PathVariable Long hobbyIndex) {
        User user = userService.getById(userIndex);
        Hobby hobby = hobbyService.getById(hobbyIndex);

        if(!hobbyOfUserService.isHobby(user.getId(), hobby.getId()))
            throw new NotExistedHobbyException();

        hobbyOfUserService.deleteHobby(user.getId(), hobby.getId());

        return ResponseEntity.ok().build();
    }
}
