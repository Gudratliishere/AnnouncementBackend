package az.mapacademy.announcement.service;

import az.mapacademy.announcement.dao.UserDao;
import az.mapacademy.announcement.dto.*;
import az.mapacademy.announcement.entity.User;
import az.mapacademy.announcement.exception.ConflictException;
import az.mapacademy.announcement.exception.NotFoundException;
import az.mapacademy.announcement.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
    private final JwtService jwtService;

    public LoginResponse login(UserLoginRequest loginRequest) {
        log.info("User login request: {}", loginRequest);

        User user = userDao.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found"));

        //dushunek ki login ve password dogrudur

        String token = jwtService.generateAccessToken(user);

        return new LoginResponse(token);
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

    public Optional<User> getByUsername(String username) {
        return userDao.findByUsername(username);
    }

    private void checkUsernameExists(User user) {
        userDao.findByUsername(user.getUsername())
                .ifPresent(user1 ->
                {
                    throw new ConflictException("Username already exists");
                });
    }
}
