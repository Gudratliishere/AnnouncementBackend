package az.mapacademy.announcement.controller;

import az.mapacademy.announcement.dto.AnnouncementRequest;
import az.mapacademy.announcement.dto.AnnouncementResponse;
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
    public void create (@RequestBody AnnouncementRequest request) {
        announcementService.createAnnouncement(request);
    }
}
