package az.mapacademy.announcement.dao;

import az.mapacademy.announcement.entity.Announcement;
import az.mapacademy.announcement.enums.SortDirection;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @author : Dunay Gudratli
 * @since : 04.03.2025
 **/
public interface AnnouncementDao {
    Page<Announcement> findAll(int page, int size, SortDirection sortCreatedDate, String name, String description);

    Page<Announcement> findAllByUsername(int page, int size, String username);

    Announcement create(Announcement announcement);

    void update(Announcement announcement);

    void delete(Long announcementId);

    Optional<Announcement> findById(Long announcementId);

    Integer getTotalAnnouncementsCount();
}
