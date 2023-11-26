package com.capstonedesign.inhalife.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReadFriendRecommendResponse {

    private Long userId;
    private String departmentName;
    private String nickname;
    private int schoolYear;
    private int age;
    private boolean gender;
    private List<String> hobbyList;
}
