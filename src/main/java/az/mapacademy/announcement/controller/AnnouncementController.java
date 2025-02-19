package az.mapacademy.announcement.controller;

import az.mapacademy.announcement.dto.AnnouncementResponse;
import az.mapacademy.announcement.dto.CreateAnnouncementRequest;
import az.mapacademy.announcement.dto.UpdateAnnouncementRequest;
import az.mapacademy.announcement.service.AnnouncementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 13.02.2025
 **/
@Slf4j
@RestController
@RequestMapping("api/v1/announcements")
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @GetMapping
    public List<AnnouncementResponse> getAnnouncements() {
        log.info("Get announcements API is called");
        return announcementService.getAllAnnouncements();
    }

    @PostMapping
    public void create(@RequestBody @Valid CreateAnnouncementRequest request) {
        log.info("Create announcement API is called, request: {}", request);
        announcementService.createAnnouncement(request);
    }

    @PutMapping("/{announcementId}") //path variable
    public void update(@PathVariable("announcementId") Long announcementId,
                       @RequestBody UpdateAnnouncementRequest request) {
        log.info("Update announcement API is called, announcementId: {}, request: {}",
                announcementId, request);
        announcementService.updateAnnouncement(announcementId, request);
    }

    @DeleteMapping("/{announcementId}")
    public void delete(@PathVariable("announcementId") Long announcementId) {
        log.info("Delete announcement API is called, announcementId: {}", announcementId);
        announcementService.deleteAnnouncement(announcementId);
    }

    @GetMapping("/{announcementId}")
    public AnnouncementResponse getById(@PathVariable("announcementId") Long announcementId) {
        log.info("Get announcement API is called, announcementId: {}", announcementId);
        return announcementService.getById(announcementId);
    }
}
