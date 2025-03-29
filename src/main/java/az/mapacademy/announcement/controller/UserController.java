package az.mapacademy.announcement.controller;

import az.mapacademy.announcement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Dunay Gudratli
 * @since : 18.03.2025
 **/
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
}
