package az.mapacademy.announcement.service;

import az.mapacademy.announcement.dao.UserDao;
import az.mapacademy.announcement.dto.*;
import az.mapacademy.announcement.entity.User;
import az.mapacademy.announcement.exception.ConflictException;
import az.mapacademy.announcement.exception.NotFoundException;
import az.mapacademy.announcement.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author : Dunay Gudratli
 * @since : 13.03.2025
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final UserMapper userMapper;

    public LoginResponse login(UserLoginRequest loginRequest) {
        log.info("User login request: {}", loginRequest);

        return new LoginResponse(UUID.randomUUID().toString());
    }

    public UserResponse create(UserRegisterRequest request) {
        var user = userMapper.toUser(request);
        checkUsernameExists(user);

        user = userDao.save(user);
        return userMapper.toResponse(user);
    }

    public UserResponse update(Long id, UserUpdateRequest request) {
        var user = userDao.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        userMapper.populate(request, user);
        user = userDao.save(user);
        return userMapper.toResponse(user);
    }

    private void checkUsernameExists(User user) {
        userDao.findByUsername(user.getUsername())
                .ifPresent(user1 ->
                {
                    throw new ConflictException("Username already exists");
                });
    }
}
