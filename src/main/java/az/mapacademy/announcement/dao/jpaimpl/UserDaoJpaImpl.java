package az.mapacademy.announcement.dao.jpaimpl;

import az.mapacademy.announcement.dao.UserDao;
import az.mapacademy.announcement.entity.User;
import az.mapacademy.announcement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Dunay Gudratli
 * @since : 13.03.2025
 **/
@Repository
@RequiredArgsConstructor
public class UserDaoJpaImpl implements UserDao {
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
