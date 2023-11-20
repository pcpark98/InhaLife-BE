package com.capstonedesign.inhalife.user;

import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.department.service.DepartmentService;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.dto.request.LoginRequest;
import com.capstonedesign.inhalife.user.dto.request.SignUpRequest;
import com.capstonedesign.inhalife.user.dto.response.LoginResponse;
import com.capstonedesign.inhalife.user.dto.response.ReadUserResponse;
import com.capstonedesign.inhalife.user.service.UserService;
import com.capstonedesign.inhalife.user.util.SessionUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Tag(name = "유저 정보 API", description = "유저 정보 관련 API")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final DepartmentService departmentService;

    @Operation(summary = "회원 가입 API", description = "유저의 회원 가입을 처리합니다.")
    @Parameters({
            @Parameter(name = "email", description = "유저의 이메일"),
            @Parameter(name = "password", description = "유저의 비밀번호"),
            @Parameter(name = "departmentId", description = "유저의 소속 학과 id"),
            @Parameter(name = "nickname", description = "유저의 닉네임"),
            @Parameter(name = "schoolYear", description = "유저의 학년"),
            @Parameter(name = "age", description = "유저의 나이"),
            @Parameter(name = "gender", description = "유저의 성별")
    })
    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(
            @RequestBody @Valid SignUpRequest request) {
        Department department = departmentService.getById(request.getDepartmentId());

        User user = new User(request.getEmail(), request.getPassword(), department, request.getNickname(), request.getSchoolYear(), request.getAge(), request.isGender());
        userService.signUp(user);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "로그인 API", description = "유저의 로그인을 처리합니다.")
    @Parameters({
            @Parameter(name = "email", description = "유저의 이메일"),
            @Parameter(name = "password", description = "유저의 비밀번호")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody @Valid LoginRequest request) {
        User user = userService.login(request.getEmail(), request.getPassword());

        String uuid = UUID.randomUUID().toString();
        SessionUtil.setAttribute(uuid, String.valueOf(user.getId()));

        return ResponseEntity.ok()
                .header("set-cookie", "sessionId="+uuid+"; Path=/; Secure; HttpOnly; SameSite=None")
                .body(new LoginResponse(user.getId(), user.getNickname(), user.getDepartment().getName()));
    }

    @Operation(summary = "유저 정보 조회 API", description = "유저의 정보를 조회합니다.")
    @Parameter(name = "userIndex", description = "정보를 조회할 유저의 id")
    @GetMapping("/user/{userIndex}")
    public ResponseEntity<ReadUserResponse> readUser(
            @PathVariable Long userIndex) {
        User user = userService.getById(userIndex);

        return ResponseEntity.ok(
                new ReadUserResponse(
                        user.getId(),
                        user.getDepartment().getName(),
                        user.getEmail(),
                        user.getNickname(),
                        user.getSchoolYear(),
                        user.getAge(),
                        user.isGender()));
    }
}
