package az.mapacademy.announcement.service;

import az.mapacademy.announcement.dao.AnnouncementDao;
import az.mapacademy.announcement.dto.AnnouncementRequest;
import az.mapacademy.announcement.dto.AnnouncementResponse;
import az.mapacademy.announcement.entity.Announcement;
import az.mapacademy.announcement.mapper.AnnouncementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 13.02.2025
 **/
@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementDao announcementDao;
    private final AnnouncementMapper announcementMapper;

    public List<AnnouncementResponse> getAllAnnouncements() {
        List<Announcement> announcements = announcementDao.findAll();
        return announcementMapper.toResponseList(announcements);
    }

    public void createAnnouncement(AnnouncementRequest request) {
        Announcement announcement = announcementMapper.toEntity(request);
        announcementDao.create(announcement);
    }
}
