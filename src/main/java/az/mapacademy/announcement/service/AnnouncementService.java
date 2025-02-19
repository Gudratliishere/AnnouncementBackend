package az.mapacademy.announcement.service;

import az.mapacademy.announcement.dao.AnnouncementDao;
import az.mapacademy.announcement.dto.AnnouncementResponse;
import az.mapacademy.announcement.dto.CreateAnnouncementRequest;
import az.mapacademy.announcement.dto.UpdateAnnouncementRequest;
import az.mapacademy.announcement.entity.Announcement;
import az.mapacademy.announcement.mapper.AnnouncementMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 13.02.2025
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementDao announcementDao;
    private final AnnouncementMapper announcementMapper;

    public List<AnnouncementResponse> getAllAnnouncements() {
        List<Announcement> announcements = announcementDao.findAll();
        log.info("Announcements found: {}", announcements);

        return announcementMapper.toResponseList(announcements);
    }

    public void createAnnouncement(CreateAnnouncementRequest request) {
        Announcement announcement = announcementMapper.toEntity(request);
        log.info("Announcement create entity: {}", announcement);

        announcementDao.create(announcement);
    }

    public void updateAnnouncement(Long announcementId, UpdateAnnouncementRequest request) {
        Announcement announcement = announcementMapper.toEntity(announcementId, request);
        log.info("Announcement update entity: {}", announcement);

        announcementDao.update(announcement);
    }

    public void deleteAnnouncement(Long announcementId) {
        announcementDao.delete(announcementId);
    }

    public AnnouncementResponse getById (Long announcementId) {
        Announcement announcement = announcementDao.getById(announcementId);
        log.info("Announcement found: {}", announcement);

        return announcementMapper.toResponse(announcement);
    }
}
