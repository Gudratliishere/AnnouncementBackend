package az.mapacademy.announcement.dao;

import az.mapacademy.announcement.entity.User;

import java.util.Optional;

/**
 * @author : Dunay Gudratli
 * @since : 13.03.2025
 **/
public interface UserDao {
    User save(User user);

    Optional<User> findById(Long id);

    void delete(Long id);
}
