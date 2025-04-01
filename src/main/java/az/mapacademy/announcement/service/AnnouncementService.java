package az.mapacademy.announcement.service;

import az.mapacademy.announcement.dao.AnnouncementDao;
import az.mapacademy.announcement.dto.AnnouncementResponse;
import az.mapacademy.announcement.dto.BaseResponse;
import az.mapacademy.announcement.dto.CreateAnnouncementRequest;
import az.mapacademy.announcement.dto.UpdateAnnouncementRequest;
import az.mapacademy.announcement.entity.Announcement;
import az.mapacademy.announcement.enums.SortDirection;
import az.mapacademy.announcement.exception.NotFoundException;
import az.mapacademy.announcement.mapper.AnnouncementMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : Dunay Gudratli
 * @since : 13.02.2025
 **/
@Slf4j
@Service
public class AnnouncementService {
    private final AnnouncementDao announcementDao;
    private final AnnouncementMapper announcementMapper;
    private final UserService userService;

    public AnnouncementService(
            @Qualifier("announcementDaoJpaImpl") AnnouncementDao announcementDao,
            AnnouncementMapper announcementMapper,
            UserService userService) {
        this.announcementDao = announcementDao;
        this.announcementMapper = announcementMapper;
        this.userService = userService;
    }

    public BaseResponse<List<AnnouncementResponse>> getAllAnnouncements(
            int page, int size, SortDirection sortCreatedDate, String name, String description) {
        Page<Announcement> announcementsPage = announcementDao.findAll(page, size, sortCreatedDate, name, description);
        List<Announcement> announcements = announcementsPage.getContent();
        log.info("Announcements found: {}", announcements);

        var announcementList = announcementMapper.toResponseList(announcements);

        BaseResponse<List<AnnouncementResponse>> baseResponse = new BaseResponse<>();
        baseResponse.setData(announcementList);
        baseResponse.setPageCount(announcementsPage.getTotalPages());
        return baseResponse;
    }

    public AnnouncementResponse createAnnouncement(CreateAnnouncementRequest request) {
        Announcement announcement = announcementMapper.toEntity(request);
        log.info("Announcement create entity: {}", announcement);

        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        var user = userService.getByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
        announcement.setUser(user);

        announcement = announcementDao.create(announcement);
        return announcementMapper.toResponse(announcement);
    }

    public void updateAnnouncement(Long announcementId, UpdateAnnouncementRequest request) {
        Optional<Announcement> optAnnouncement = announcementDao.findById(announcementId);
        Announcement announcement = optAnnouncement.orElseThrow(() ->
                new NotFoundException("Announcement is not found with id: " + announcementId));

        announcementMapper.populate(request, announcement);
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
