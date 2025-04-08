package az.mapacademy.announcement.controller;

import az.mapacademy.announcement.dto.AnnouncementResponse;
import az.mapacademy.announcement.dto.BaseResponse;
import az.mapacademy.announcement.dto.CreateAnnouncementRequest;
import az.mapacademy.announcement.dto.UpdateAnnouncementRequest;
import az.mapacademy.announcement.enums.SortDirection;
import az.mapacademy.announcement.service.AnnouncementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public BaseResponse<List<AnnouncementResponse>> getAnnouncements(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(value = "sortByCreatedDate", required = false) SortDirection sortCreatedDate,
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "description", required = false, defaultValue = "") String description) {
        log.info("Get announcements API is called");

        return announcementService.getAllAnnouncements(page, size, sortCreatedDate, name, description);
    }

    @GetMapping("/my-announcements")
    public BaseResponse<List<AnnouncementResponse>> getMyAnnouncements(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        log.info("Get my announcements API is called");

        return announcementService.getMyAllAnnouncements(page, size);
    }

    @PostMapping
    public BaseResponse<AnnouncementResponse> create(@RequestBody @Valid CreateAnnouncementRequest request) {
        log.info("Create announcement API is called, request: {}", request);
        var response = announcementService.createAnnouncement(request);

        BaseResponse<AnnouncementResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(response);
        return baseResponse;
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
    public BaseResponse<AnnouncementResponse> getById(@PathVariable("announcementId") Long announcementId) {
        log.info("Get announcement API is called, announcementId: {}", announcementId);
        AnnouncementResponse response = announcementService.getById(announcementId);

        BaseResponse<AnnouncementResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(response);
        return baseResponse;
    }

    @PostMapping("/{announcementId}/files")
    public void uploadFile(
            @PathVariable("announcementId") Long announcementId,
            @RequestPart("file") MultipartFile multipartFile) {
        log.info("File is uploading.");
        announcementService.uploadFile(announcementId, multipartFile);
    }

    @GetMapping("/{announcementId}/files")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable("announcementId") Long announcementId) {
        log.info("File is downloading.");
        var fileResponse = announcementService.downloadFile(announcementId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileResponse.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileResponse.getName())
                .body(new ByteArrayResource(fileResponse.getData()));
    }

    @DeleteMapping("/{announcementId}/files")
    public void deleteFile(@PathVariable("announcementId") Long announcementId) {
        log.info("File is deleting.");
        announcementService.deleteFile(announcementId);
    }
}
