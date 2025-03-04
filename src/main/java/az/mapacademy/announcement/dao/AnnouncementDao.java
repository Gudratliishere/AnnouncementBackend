package az.mapacademy.announcement.dao;

import az.mapacademy.announcement.entity.Announcement;

import java.util.List;
import java.util.Optional;

/**
 * @author : Dunay Gudratli
 * @since : 04.03.2025
 **/
public interface AnnouncementDao {
    List<Announcement> findAll(int page, int size);

    void create(Announcement announcement);

    void update(Announcement announcement);

    void delete(Long announcementId);

    Optional<Announcement> findById(Long announcementId);

    Integer getTotalAnnouncementsCount();
}
