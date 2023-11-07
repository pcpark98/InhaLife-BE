package com.capstonedesign.inhalife.user;

import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.department.service.DepartmentService;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.dto.request.SignUpRequest;
import com.capstonedesign.inhalife.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
