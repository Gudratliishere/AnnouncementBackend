package az.mapacademy.announcement.service;

import az.mapacademy.announcement.dao.AnnouncementDao;
import az.mapacademy.announcement.dto.AnnouncementResponse;
import az.mapacademy.announcement.dto.BaseResponse;
import az.mapacademy.announcement.dto.CreateAnnouncementRequest;
import az.mapacademy.announcement.dto.UpdateAnnouncementRequest;
import az.mapacademy.announcement.entity.Announcement;
import az.mapacademy.announcement.exception.NotFoundException;
import az.mapacademy.announcement.mapper.AnnouncementMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public BaseResponse<List<AnnouncementResponse>> getAllAnnouncements(int page, int size) {
        List<Announcement> announcements = announcementDao.findAll(page, size);
        log.info("Announcements found: {}", announcements);

        Integer totalCount = announcementDao.getTotalAnnouncementsCount();//12
        log.info("Total announcements count: {}", totalCount);
        int pageCount;
        if (totalCount % size == 0){
            pageCount = totalCount / size;
        } else {
            pageCount = totalCount / size + 1;
        }

        var announcementList = announcementMapper.toResponseList(announcements);

        BaseResponse<List<AnnouncementResponse>> baseResponse = new BaseResponse<>();
        baseResponse.setData(announcementList);
        baseResponse.setPageCount(pageCount);
        return baseResponse;
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

    public AnnouncementResponse getById(Long announcementId) {
        Optional<Announcement> optAnnouncement = announcementDao.findById(announcementId);
        Announcement announcement = optAnnouncement.orElseThrow(() ->
                new NotFoundException("Announcement is not found with id: " + announcementId));

        log.info("Announcement found: {}", announcement);

        return announcementMapper.toResponse(announcement);
    }
}
