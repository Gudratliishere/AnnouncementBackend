package az.mapacademy.announcement.dao.jpaimpl;

import az.mapacademy.announcement.dao.AnnouncementDao;
import az.mapacademy.announcement.entity.Announcement;
import az.mapacademy.announcement.enums.SortDirection;
import az.mapacademy.announcement.repository.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Dunay Gudratli
 * @since : 04.03.2025
 **/
@Slf4j
@Repository("announcementDaoJpaImpl")
@RequiredArgsConstructor
public class AnnouncementDaoJpaImpl implements AnnouncementDao {
    private final AnnouncementRepository announcementRepository;

    @Override
    public Page<Announcement> findAll(int page, int size, SortDirection sortCreatedDate, String name, String description) {
        log.info("Find All announcements method is called from jpa impl of AnnouncementDao");

        Sort sort = null;
        if (sortCreatedDate == SortDirection.ASC) {
            sort = Sort.by(Sort.Direction.ASC, "createdDate");
        } else if (sortCreatedDate == SortDirection.DESC) {
            sort = Sort.by(Sort.Direction.DESC, "createdDate");
        }

        Pageable pageable;
        if (sort != null) {
            pageable = PageRequest.of(page - 1, size, sort);
        } else {
            pageable = PageRequest.of(page - 1, size);
        }

        return announcementRepository.findAllWithSql(name, description, pageable);
    }

    @Override
    public void create(Announcement announcement) {
        log.info("Create announcement is called from jpa impl of AnnouncementDao");

        announcementRepository.save(announcement);
    }

    @Override
    public void update(Announcement announcement) {
        log.info("Update announcement is called from jpa impl of AnnouncementDao");

        announcementRepository.save(announcement);
    }

    @Override
    public void delete(Long announcementId) {
        log.info("Delete announcement is called from jpa impl of AnnouncementDao");

        announcementRepository.deleteById(announcementId);
    }

    @Override
    public Optional<Announcement> findById(Long announcementId) {
        log.info("Find announcement is called from jpa impl of AnnouncementDao");

        return announcementRepository.findById(announcementId);
    }

    @Override
    public Integer getTotalAnnouncementsCount() {
        log.info("Getting total count of announcements is called from jpa impl of AnnouncementDao");

        return (int) announcementRepository.count();
    }
}
