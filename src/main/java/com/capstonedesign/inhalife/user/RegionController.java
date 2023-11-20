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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "활동지역 API", description = "유저의 활동 지역 관련 API")
@RestController
@RequiredArgsConstructor
public class RegionController {

    private final UserService userService;
    private final RegionService regionService;
    private final RegionOfUserService regionOfUserService;

    @Operation(summary = "지역 목록 조회 API", description = "저장된 모든 지역 리스트를 조회합니다.")
    @GetMapping("/region")
    public ResponseEntity<List<GetRegionResponse>> getAllRegion() {
        List<GetRegionResponse> regionList = regionService.getAll();

        return ResponseEntity.ok(regionList);
    }

    @Operation(summary = "활동 지역 등록 API", description = "유저의 활동 지역을 등록합니다.")
    @Parameters({
            @Parameter(name = "userId", description = "활동 지역을 등록할 유저의 id"),
            @Parameter(name = "regionId", description = "등록할 지역의 id" )
    })
    @PostMapping("/region")
    public ResponseEntity<Void> setRegion(
            @RequestBody @Valid SetRegionRequest request) {

        User user = userService.getById(request.getUserId());
        Region region = regionService.getById(request.getRegionId());

        RegionOfUser regionOfUser = new RegionOfUser(user, region);
        regionOfUserService.setRegion(regionOfUser);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "유저의 활동 지역 목록 조회 API", description = "유저의 모든 활동 지역 리스트를 조회합니다.")
    @Parameter(name = "userIndex", description = "활동 지역 목록을 조회할 유저의 id")
    @GetMapping("/user/{userIndex}/regions")
    public ResponseEntity<List<GetRegionResponse>> getUsersAllRegion(
            @PathVariable Long userIndex) {
        User user = userService.getById(userIndex);

        List<GetRegionResponse> regionList = regionOfUserService.getUsersAllRegion(user.getId());

        return ResponseEntity.ok(regionList);
    }

    @Operation(summary = "활동 지역 삭제 API", description = "유저의 활동 지역을 삭제합니다.")
    @Parameters({
            @Parameter(name = "userIndex", description = "활동 지역을 삭제할 유저의 id"),
            @Parameter(name = "regionIndex", description = "삭제할 지역의 id" )
    })
    @DeleteMapping("/user/{userIndex}/region/{regionIndex}")
    public ResponseEntity<Void> deleteRegion(
            @PathVariable Long userIndex,
            @PathVariable Long regionIndex) {
        User user = userService.getById(userIndex);
        Region region = regionService.getById(regionIndex);

        if(!regionOfUserService.isRegion(user.getId(), region.getId()))
            throw new NotExistedRegionOfUserException();

        regionOfUserService.deleteRegion(user.getId(), region.getId());

        return ResponseEntity.ok().build();
    }
}
