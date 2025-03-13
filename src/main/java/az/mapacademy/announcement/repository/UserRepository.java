package az.mapacademy.announcement.repository;

import az.mapacademy.announcement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Dunay Gudratli
 * @since : 13.03.2025
 **/
public interface UserRepository extends JpaRepository<User, Long> {
}
