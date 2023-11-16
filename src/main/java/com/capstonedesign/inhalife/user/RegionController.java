package com.capstonedesign.inhalife.user;

import com.capstonedesign.inhalife.user.dto.response.GetRegionResponse;
import com.capstonedesign.inhalife.user.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RegionController {
    
    private final RegionService regionService;

    @GetMapping("/region")
    public ResponseEntity<List<GetRegionResponse>> getAllRegion() {
        List<GetRegionResponse> regionList = regionService.getAll();

        return ResponseEntity.ok(regionList);
    }
}
