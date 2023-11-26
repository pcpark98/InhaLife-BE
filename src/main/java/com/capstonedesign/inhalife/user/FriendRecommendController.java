package com.capstonedesign.inhalife.user;

import com.capstonedesign.inhalife.department.domain.Department;
import com.capstonedesign.inhalife.department.service.DepartmentService;
import com.capstonedesign.inhalife.user.domain.Hobby;
import com.capstonedesign.inhalife.user.dto.request.GetFriendRecommendationRequest;
import com.capstonedesign.inhalife.user.dto.response.ReadFriendRecommendResponse;
import com.capstonedesign.inhalife.user.service.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FriendRecommendController {

    private final HobbyService hobbyService;
    private final DepartmentService departmentService;

    // TODO: 친구 추천은 프론트에서 받아오는거까지 작업 되었음.
    /*
    @PostMapping("/friend-recommend")
    public ResponseEntity<List<ReadFriendRecommendResponse>> getFriendRecommendation(
            @RequestBody @Valid GetFriendRecommendationRequest request) {
        List<Hobby> hobbyList = new ArrayList<>();

        List<String> selectedSports = request.getSprots();
        selectedSports.forEach(sports -> {
            Hobby hobby = hobbyService.getByName(sports);
            hobbyList.add(hobby);
        });

        List<String> selectedHobbies = request.getHobbies();
        selectedHobbies.forEach(selectedHobby -> {
            Hobby hobby = hobbyService.getByName(selectedHobby);
            hobbyList.add(hobby);
        });

        Department selectedDepartment = departmentService.getByName(request.getDepartment());
        // age랑 gender도 있음.
    }

     */
}
