package com.capstonedesign.inhalife.user.dto.response;

import com.capstonedesign.inhalife.department.domain.Department;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReadFriendResponse {

    private Long friendId;
    private Long userId;
    private String departmentName;
    private String email;
    private String nickname;
    private int schoolYear;
    private int age;
    private boolean gender;
    private LocalDateTime createdAt;
}
