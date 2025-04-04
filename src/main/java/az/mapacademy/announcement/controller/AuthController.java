package az.mapacademy.announcement.controller;

import az.mapacademy.announcement.dto.*;
import az.mapacademy.announcement.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Dunay Gudratli
 * @since : 27.03.2025
 **/
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public BaseResponse<LoginResponse> login(@RequestBody @Valid UserLoginRequest loginRequest) {
        var loginResponse = authService.login(loginRequest);

        BaseResponse<LoginResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(loginResponse);
        return baseResponse;
    }

    @PostMapping("sign-up")
    public BaseResponse<UserResponse> register(@RequestBody @Valid UserRegisterRequest request) {
        var userResponse = authService.register(request);

        BaseResponse<UserResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(userResponse);
        return baseResponse;
    }
}
