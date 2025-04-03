package az.mapacademy.announcement.controller;

import az.mapacademy.announcement.dto.BaseResponse;
import az.mapacademy.announcement.dto.UserResponse;
import az.mapacademy.announcement.dto.UserUpdateRequest;
import az.mapacademy.announcement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Dunay Gudratli
 * @since : 18.03.2025
 **/
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("my-information")
    public BaseResponse<UserResponse> getMyInformation() {
        var userResponse = userService.getMyInformation();

        BaseResponse<UserResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(userResponse);
        return baseResponse;
    }

    @PutMapping
    public BaseResponse<UserResponse> update(
            @RequestBody UserUpdateRequest request) {
        var userResponse = userService.update(request);

        BaseResponse<UserResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(userResponse);
        return baseResponse;
    }
}
