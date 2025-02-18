package az.mapacademy.announcement.controller;

import az.mapacademy.announcement.dto.AnnouncementResponse;
import az.mapacademy.announcement.dto.CreateAnnouncementRequest;
import az.mapacademy.announcement.dto.UpdateAnnouncementRequest;
import az.mapacademy.announcement.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 13.02.2025
 **/
@RestController
@RequestMapping("api/v1/announcements")
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @GetMapping
    public List<AnnouncementResponse> getAnnouncements() {
        return announcementService.getAllAnnouncements();
    }

    @PostMapping
    public void create(@RequestBody CreateAnnouncementRequest request) {
        announcementService.createAnnouncement(request);
    }

    @PutMapping("/{announcementId}") //path variable
    public void update(@PathVariable("announcementId") Long announcementId,
                       @RequestBody UpdateAnnouncementRequest request) {
        announcementService.updateAnnouncement(announcementId, request);
    }

    @DeleteMapping("/{announcementId}")
    public void delete(@PathVariable("announcementId") Long announcementId) {
        announcementService.deleteAnnouncement(announcementId);
    }

    @GetMapping("/{announcementId}")
    public AnnouncementResponse getById(@PathVariable("announcementId") Long announcementId) {
        return announcementService.getById(announcementId);
    }
}
