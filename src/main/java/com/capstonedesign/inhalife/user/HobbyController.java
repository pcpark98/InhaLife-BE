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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "취미 API", description = "유저의 취미 관련 API")
@RestController
@RequiredArgsConstructor
public class HobbyController {

    private final UserService userService;
    private final HobbyService hobbyService;
    private final HobbyOfUserService hobbyOfUserService;

    @Operation(summary = "취미 목록 조회 API", description = "저장된 모든 취미 리스트를 조회합니다.")
    @GetMapping("/hobby")
    public ResponseEntity<List<GetHobbyResponse>> getAllHobby() {
        List<GetHobbyResponse> hobbyList = hobbyService.getAll();

        return ResponseEntity.ok(hobbyList);
    }

    @Operation(summary = "취미 등록 API", description = "유저의 취미를 등록합니다.")
    @Parameters({
            @Parameter(name = "userId", description = "취미를 등록할 유저의 id"),
            @Parameter(name = "hobbyId", description = "등록할 취미의 id" )
    })
    @PostMapping("/hobby")
    public ResponseEntity<Void> setHobby(
            @RequestBody @Valid SetHobbyRequest request) {

        User user = userService.getById(request.getUserId());
        Hobby hobby = hobbyService.getById(request.getHobbyId());

        HobbyOfUser hobbyOfUser = new HobbyOfUser(user, hobby);
        hobbyOfUserService.setHobby(hobbyOfUser);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "유저의 취미 목록 조회 API", description = "유저의 모든 취미 리스트를 조회합니다.")
    @Parameter(name = "userIndex", description = "취미 목록을 조회할 유저의 id")
    @GetMapping("/user/{userIndex}/hobbies")
    public ResponseEntity<List<GetHobbyResponse>> getUsersAllHobby(
            @PathVariable Long userIndex) {
        User user = userService.getById(userIndex);

        List<GetHobbyResponse> hobbyList = hobbyOfUserService.getAllHobby(user.getId());

        return ResponseEntity.ok(hobbyList);
    }

    @Operation(summary = "취미 삭제 API", description = "유저의 취미를 삭제합니다.")
    @Parameters({
            @Parameter(name = "userIndex", description = "취미를 삭제할 유저의 id"),
            @Parameter(name = "hobbyIndex", description = "삭제할 취미의 id" )
    })
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
