package com.capstonedesign.inhalife.user;

import com.capstonedesign.inhalife.user.domain.Hobby;
import com.capstonedesign.inhalife.user.domain.HobbyOfUser;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.dto.request.SetHobbyRequest;
import com.capstonedesign.inhalife.user.service.HobbyOfUserService;
import com.capstonedesign.inhalife.user.service.HobbyService;
import com.capstonedesign.inhalife.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/hobby")
@RequiredArgsConstructor
public class HobbyController {

    private final UserService userService;
    private final HobbyService hobbyService;
    private final HobbyOfUserService hobbyOfUserService;

    @PostMapping
    public ResponseEntity<Void> setHobby(
            @RequestBody @Valid SetHobbyRequest request) {

        User user = userService.getById(request.getUserId());
        Hobby hobby = hobbyService.getById(request.getHobbyId());

        HobbyOfUser hobbyOfUser = new HobbyOfUser(user, hobby);
        hobbyOfUserService.setHobby(hobbyOfUser);

        return ResponseEntity.ok().build();
    }
}
