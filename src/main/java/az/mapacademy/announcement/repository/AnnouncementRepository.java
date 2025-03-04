package az.mapacademy.announcement.repository;

import az.mapacademy.announcement.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Dunay Gudratli
 * @since : 04.03.2025
 **/
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
}
