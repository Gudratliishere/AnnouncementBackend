package az.mapacademy.announcement.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : Dunay Gudratli
 * @since : 13.02.2025
 **/
@Data
public class Announcement {
    private Long announcementId;
    private String name;
    private String description;
    private Long announcementNumber;
    private Double price;
    private String phoneNumber;
    private String sellerFullName;
    private Boolean delivery;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private City city;
    private Category category;
}
