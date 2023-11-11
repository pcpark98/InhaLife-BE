package com.capstonedesign.inhalife.user;

import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.department.service.DepartmentService;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.dto.request.LoginRequest;
import com.capstonedesign.inhalife.user.dto.request.SignUpRequest;
import com.capstonedesign.inhalife.user.dto.response.LoginResponse;
import com.capstonedesign.inhalife.user.service.UserService;
import com.capstonedesign.inhalife.user.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final DepartmentService departmentService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(
            @RequestBody @Valid SignUpRequest request) {
        Department department = departmentService.getById(request.getDepartmentId());

        User user = new User(request.getEmail(), request.getPassword(), department, request.getNickname(), request.getSchoolYear(), request.getAge(), request.isGender());
        userService.signUp(user);

        return ResponseEntity.ok().build();
    }

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
}
