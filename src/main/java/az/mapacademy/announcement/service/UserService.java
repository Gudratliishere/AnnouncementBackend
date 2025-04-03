package az.mapacademy.announcement.service;

import az.mapacademy.announcement.dao.UserDao;
import az.mapacademy.announcement.dto.UserResponse;
import az.mapacademy.announcement.dto.UserUpdateRequest;
import az.mapacademy.announcement.entity.User;
import az.mapacademy.announcement.exception.NotFoundException;
import az.mapacademy.announcement.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public UserResponse update(UserUpdateRequest request) {
        var username = getUsername();
        var user = getByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
        userMapper.populate(request, user);
        user = userDao.save(user);
        return userMapper.toResponse(user);
    }

    public Optional<User> getByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public UserResponse getMyInformation() {
        String username = getUsername();
        var user = getByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return userMapper.toResponse(user);
    }

    private static String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}
