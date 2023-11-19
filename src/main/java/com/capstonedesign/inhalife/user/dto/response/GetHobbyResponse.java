package com.capstonedesign.inhalife.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetHobbyResponse {

    private Long hobbyId;

    private String hobbyName;
}
