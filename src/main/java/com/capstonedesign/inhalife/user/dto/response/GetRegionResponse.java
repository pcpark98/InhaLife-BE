package com.capstonedesign.inhalife.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetRegionResponse {

    private Long regionId;

    private String regionName;
}
