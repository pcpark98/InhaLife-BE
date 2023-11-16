package com.capstonedesign.inhalife.user;

import com.capstonedesign.inhalife.user.domain.Region;
import com.capstonedesign.inhalife.user.domain.RegionOfUser;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.dto.request.SetRegionRequest;
import com.capstonedesign.inhalife.user.dto.response.GetRegionResponse;
import com.capstonedesign.inhalife.user.exception.NotExistedRegionOfUserException;
import com.capstonedesign.inhalife.user.service.RegionOfUserService;
import com.capstonedesign.inhalife.user.service.RegionService;
import com.capstonedesign.inhalife.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RegionController {

    private final UserService userService;
    private final RegionService regionService;
    private final RegionOfUserService regionOfUserService;

    @GetMapping("/region")
    public ResponseEntity<List<GetRegionResponse>> getAllRegion() {
        List<GetRegionResponse> regionList = regionService.getAll();

        return ResponseEntity.ok(regionList);
    }

    @PostMapping("/region")
    public ResponseEntity<Void> setRegion(
            @RequestBody @Valid SetRegionRequest request) {

        User user = userService.getById(request.getUserId());
        Region region = regionService.getById(request.getRegionId());

        RegionOfUser regionOfUser = new RegionOfUser(user, region);
        regionOfUserService.setRegion(regionOfUser);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userIndex}/regions")
    public ResponseEntity<List<GetRegionResponse>> getUsersAllRegion(
            @PathVariable("userIndex") Long userId) {
        User user = userService.getById(userId);

        List<GetRegionResponse> regionList = regionOfUserService.getUsersAllRegion(user.getId());

        return ResponseEntity.ok(regionList);
    }

    @DeleteMapping("/user/{userIndex}/region/{regionIndex}")
    public ResponseEntity<Void> deleteRegion(
            @PathVariable("userIndex") Long userId,
            @PathVariable("regionIndex") Long regionId) {
        User user = userService.getById(userId);
        Region region = regionService.getById(regionId);

        if(!regionOfUserService.isRegion(user.getId(), region.getId()))
            throw new NotExistedRegionOfUserException();

        regionOfUserService.deleteRegion(user.getId(), region.getId());

        return ResponseEntity.ok().build();
    }
}
