package az.mapacademy.announcement.service;

import az.mapacademy.announcement.dao.UserDao;
import az.mapacademy.announcement.dto.LoginResponse;
import az.mapacademy.announcement.dto.UserLoginRequest;
import az.mapacademy.announcement.dto.UserRegisterRequest;
import az.mapacademy.announcement.dto.UserResponse;
import az.mapacademy.announcement.entity.User;
import az.mapacademy.announcement.exception.ConflictException;
import az.mapacademy.announcement.exception.NotFoundException;
import az.mapacademy.announcement.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : Dunay Gudratli
 * @since : 27.03.2025
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserMapper userMapper;
    private final UserDao userDao;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(UserLoginRequest loginRequest) {
        log.info("User login request: {}", loginRequest);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()));
        //xeta atmasa, davam edir

        User user = userDao.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found"));

        String token = jwtService.generateAccessToken(user);

        return new LoginResponse(token);
    }

    public UserResponse register(UserRegisterRequest request) {
        var user = userMapper.toUser(request);
        checkUsernameExists(user);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

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
