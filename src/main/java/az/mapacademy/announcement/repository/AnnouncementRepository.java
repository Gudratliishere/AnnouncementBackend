package az.mapacademy.announcement.repository;

import az.mapacademy.announcement.entity.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Dunay Gudratli
 * @since : 04.03.2025
 **/
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    Page<Announcement> findAllByNameContainingAndDescriptionContaining(String name, String description, Pageable pageable);
}
