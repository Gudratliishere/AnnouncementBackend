package az.mapacademy.announcement.service;

import az.mapacademy.announcement.dao.UserDao;
import az.mapacademy.announcement.dto.UserRegisterRequest;
import az.mapacademy.announcement.dto.UserResponse;
import az.mapacademy.announcement.dto.UserUpdateRequest;
import az.mapacademy.announcement.exception.NotFoundException;
import az.mapacademy.announcement.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author : Dunay Gudratli
 * @since : 13.03.2025
 **/
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final UserMapper userMapper;

    public UserResponse create(UserRegisterRequest request) {
        var user = userMapper.toUser(request);
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
}
