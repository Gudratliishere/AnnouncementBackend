package az.mapacademy.announcement.service;

import az.mapacademy.announcement.dao.AnnouncementDao;
import az.mapacademy.announcement.dto.AnnouncementDto;
import az.mapacademy.announcement.entity.Announcement;
import az.mapacademy.announcement.mapper.AnnouncementMapper;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 13.02.2025
 **/
public class AnnouncementService {
    private final AnnouncementDao announcementDao = new AnnouncementDao();
    private final AnnouncementMapper announcementMapper = new AnnouncementMapper();

    public List<AnnouncementDto> getAllAnnouncements() {
        List<Announcement> announcements = announcementDao.findAll();
        return announcementMapper.toDtoList(announcements);
    }
}
